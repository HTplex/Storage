/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2014 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */

package playground.gregor.casim.simulation.physics;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;

public class CASingleLaneDensityEstimatorSPH implements
		CADensityEstimatorKernel {
	private static Logger log = Logger
			.getLogger(CASingleLaneDensityEstimatorSPH.class);
	private int misses = 0;
	private AbstractCANetwork net;
	public static int H = 6;

	public CASingleLaneDensityEstimatorSPH(AbstractCANetwork net) {
		this.net = net;
	}

	@Override
	public double estRho(CAMoveableEntity e) {
		CANetworkEntity ett = e.getCurrentCANetworkEntity();
		if (ett instanceof CASingleLaneNode) {
			return e.getRho();
		}
		CASingleLaneLink l = (CASingleLaneLink) ett;
		CAMoveableEntity[] parts = l.getParticles();

		int pos = e.getPos();
		int dir = e.getDir();
		if (parts[pos] != e) {
			// agent on link?
			misses++;
			return e.getRho();
		}
		double[] traRho = { 0., bSplinesKernel(0) };

		traverseLink(parts, dir, pos + dir, traRho);
		if (!traverseLink(parts, dir, pos + dir, traRho)) {
			CASingleLaneNode n;
			if (dir == 1) {
				n = (CASingleLaneNode) l.getDownstreamCANode();
			} else {
				n = (CASingleLaneNode) l.getUpstreamCANode();
			}
			traRho[0]++;
			if (n.peekForAgent() != null) {
				traRho[1] += 2 * bSplinesKernel(traRho[0]);
			}

			Id<Link> nextId;
			if (traRho[0] < 2 * H && (nextId = e.getNextLinkId()) != null) {
				CASingleLaneLink next = (CASingleLaneLink) this.net
						.getCALink(nextId);
				CAMoveableEntity[] nextParts = next.getParticles();
				CANode nn = next.getUpstreamCANode();
				int nextDir;
				int nextPos;
				if (n == nn) {
					nextDir = 1;
					nextPos = 0;
				} else {
					nextDir = -1;
					nextPos = nextParts.length - 1;
				}
				traverseLink(nextParts, nextDir, nextPos, traRho);
			}
		}

		return AbstractCANetwork.RHO_HAT * traRho[1];
	}

	private boolean traverseLink(CAMoveableEntity[] parts, int dir, int idx,
			double[] traRho) {
		int toMx = dir == -1 ? 0 : parts.length - 1;
		if (idx - dir == toMx) {
			return false;
		}
		for (; idx != toMx; idx += dir) {
			traRho[0]++;
			if (parts[idx] != null) {
				traRho[1] += 2 * bSplinesKernel(traRho[0]);
			}
			if (traRho[0] >= 2 * H) {
				return true;
			}
		}
		return false;
	}

	private double bSplinesKernel(final double r) {
		final double sigma = 2d / 3d; // 1d normalization
		final double v = 1d; // 1d
		final double term1 = sigma / Math.pow(H, v);
		double q = r / H;
		if (q <= 1d) {
			final double term2 = 1d - 3d / 2d * Math.pow(q, 2d) + 3d / 4d
					* Math.pow(q, 3d);
			return term1 * term2;
		} else if (q <= 2d) {
			final double term2 = 1d / 4d * Math.pow(2d - q, 3);
			return term1 * term2;
		}
		return 0;

	}

	@Override
	public void report() {
		log.info("misses in this iteration: " + this.misses);
	}
}
