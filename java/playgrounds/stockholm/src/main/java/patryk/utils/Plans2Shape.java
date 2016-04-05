package patryk.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Population;
import org.matsim.api.core.v01.population.PopulationFactory;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.population.PopulationReaderMatsimV5;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.utils.geometry.geotools.MGC;
import org.matsim.utils.gis.matsim2esri.plans.SelectedPlans2ESRIShape;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


public class Plans2Shape {

	public static void main(String[] args) {
        Config config = ConfigUtils.createConfig();
        config.network().setInputFile("prep_extern/input/network_v09.xml");
        Scenario scenario = ScenarioUtils.loadScenario(config);
        Network network = scenario.getNetwork();
        
        CoordinateReferenceSystem crs = MGC.getCRS("EPSG:3857");    // EPSG Code 
        
        PopulationReaderMatsimV5 reader = new PopulationReaderMatsimV5(scenario);
        reader.readFile("600.plans.xml.gz");
        
        List<String> Ids = Arrays.asList("710434-718206_0",	"710434-710244_0",	"712870-712706_0",	"712801-712706_0",	"710443-712624_7",	"710443-720129_0",	"712623-710462_0",	"710363-710462_0",	"710344-710462_0",	"710249-710462_0",	"710404-710462_0",	"710428-710462_0",	"718130-710462_0",	"710445-710302_0",	"710445-721110_0",	"710445-723138_0",	"712625-712727_0",	"710434-712625_0",	"718634-712625_0",	"712305-710228_0",	"711429-710269_0",	"711509-710228_0",	"710285-710228_0",	"712336-710228_1",	"716331-710228_0",	"718430-710303_0",	"710228-710243_0",	"720156-718422_0",	"720122-722122_0",	"710252-718422_0",	"712634-718422_0",	"721188-722122_0",	"712630-712330_0",	"720122-720103_0",	"711404-720122_0",	"712503-718304_0",	"720133-718303_0",	"718303-718303_0",	"710202-718303_0",	"712317-718304_0",	"718606-718304_0",	"718703-718304_0",	"712623-718304_0",	"710310-718304_0",	"720173-718304_0",	"718442-721121_0",	"722137-716305_0",	"712334-716305_0",	"710266-716305_0",	"711506-716305_0",	"716311-710269_0",	"712315-716305_0",	"710319-716305_0",	"718302-716305_0",	"716310-716305_0",	"710271-716305_0",	"710290-716305_0",	"716212-716305_0",	"710418-716305_0",	"716320-716305_0",	"724123-716305_0",	"710262-716305_0",	"711709-716305_0",	"711710-716305_0",	"713914-716305_0",	"719119-716305_0",	"719112-716305_0",	"710272-710269_0",	"712309-716305_0",	"711417-716305_0",	"718638-716305_0",	"711413-710269_0",	"716330-716305_0",	"710230-716305_0",	"712325-716305_0",	"718619-716305_0",	"710256-716305_0",	"711708-716305_0",	"720178-722122_0",	"711720-722122_0",	"723138-722122_0",	"722137-722122_0",	"720150-720188_0",	"720150-720160_0",	"718130-720150_0",	"718428-718304_0",	"718305-718428_0",	"710258-710243_1",	"710265-718433_0",	"710263-718426_0",	"716033-716007_0",	"716015-716007_0",	"712810-716007_0",	"713921-716007_0",	"711716-716007_0",	"711526-716007_0",	"718828-716007_0",	"716032-718428_0",	"716032-710232_0",	"718319-723180_0",	"718131-713805_0",	"718246-713804_0",	"713819-710304_0",	"713817-713803_0",	"718633-713803_0",	"713819-710353_0",	"713822-712707_0",	"713821-713805_0",	"712730-713822_0",	"713824-710356_0",	"713824-710302_0",	"713824-713608_0",	"713824-710343_0",	"713824-710332_0",	"710235-713824_0",	"713808-720110_0",	"713817-713608_0",	"713817-720109_0",	"713606-713817_0",	"712712-713817_0",	"713826-713808_0",	"712301-713817_0",	"713813-713817_1",	"713823-713817_0",	"713618-713817_0",	"712329-713817_0",	"711716-713817_0",	"713634-713817_0",	"712008-713817_0",	"713816-713817_1",	"713620-713817_0",	"712607-713817_0",	"710350-713817_0",	"713608-713817_0",	"713641-713817_0",	"713813-713817_0",	"713811-713817_0",	"710352-713817_0",	"713603-713817_0",	"712602-713817_0",	"713817-713817_0",	"713801-713817_0",	"713821-713817_0",	"713804-713817_0",	"718148-713817_0",	"713810-713817_0",	"713616-713817_0",	"718103-713817_0",	"718225-713817_0",	"718242-713817_0",	"713807-710412_0",	"713817-718406_0",	"713807-718201_0",	"713807-710356_0",	"713817-720116_0",	"719105-710270_0",	"719102-718408_0",	"712747-712624_2",	"710418-712624_0",	"712632-712624_2",	"712736-712624_1",	"710441-712624_6",	"710416-712624_0",	"718126-712624_1",	"710449-712624_1",	"710369-712624_0",	"716310-712624_0",	"710202-712624_0",	"710446-712624_0",	"718111-712624_0",	"710456-712624_0",	"710406-712624_0",	"712747-712624_1",	"713911-712624_0",	"712745-712624_0",	"712714-712624_0",	"718106-712624_0",	"718115-712624_0",	"710425-712624_2",	"710318-712624_0",	"710446-712624_2",	"712707-712624_3",	"712513-712624_0",	"710439-712624_2",	"710448-712624_9",	"710319-712624_0",	"712625-712624_6",	"712623-712624_2",	"710274-712624_0",	"718102-712624_0",	"712510-712624_0",	"712870-712624_1",	"712747-712624_3",	"710457-712624_0",	"719201-712624_0",	"718156-712624_0",	"718248-712624_0",	"712727-712624_4",	"719215-712624_0",	"710259-712624_0",	"713801-712624_0",	"718228-712624_0",	"712631-712624_3",	"718141-712624_1",	"713622-712624_0",	"713633-712624_0",	"712743-712624_1",	"718612-712624_0",	"718130-712624_1",	"710231-712624_0",	"718632-712624_0",	"712810-712624_0",	"710458-712624_1",	"710440-712624_0",	"722183-712624_0",	"718217-712624_0",	"710434-712624_4",	"710441-712624_7",	"718415-712624_0",	"712623-712503_0",	"713801-712623_0",	"710313-712623_1",	"718126-712623_0",	"712635-712623_0",	"712622-712623_0",	"712623-712628_3",	"712741-712623_0",	"712623-716016_0",	"712623-712623_4",	"712627-716312_0",	"712627-712623_0",	"712627-710462_0",	"712627-718216_0",	"712627-718427_0",	"712632-712627_0",	"712810-712627_0",	"712630-712627_0",	"718116-712627_0",	"712631-712613_0",	"712631-720110_0",	"712631-712623_1",	"718120-712612_0",	"718144-712608_0",	"716002-712608_0",	"712608-712608_7",	"712714-712608_0",	"712608-718206_0",	"712616-712608_0",	"710432-712608_0",	"712608-710304_0",	"712608-712623_1",	"712608-712005_0",	"712608-712608_8",	"712608-712608_2",	"712614-712608_1",	"718220-712608_0",	"712740-712608_0",	"712724-712608_0",	"712614-712608_0",	"710407-712616_0",	"710266-712608_0",	"710367-712608_0",	"713634-712608_0",	"712715-712616_0",	"712721-712608_0",	"712608-710265_0",	"712632-712612_1",	"712612-712612_2",	"712008-712611_0",	"718103-712611_0",	"712609-712612_0",	"712602-712612_0",	"712708-712628_0",	"721183-712624_0",	"712710-712624_0",	"718142-712628_0",	"718128-712624_0",	"712730-712628_2",	"718151-712624_0",	"712635-710414_1",	"712635-712722_0",	"712635-712608_0",	"713806-712632_0",	"712632-710302_1",	"712632-712624_3",	"712632-710320_1",	"718149-712632_0",	"712612-710323_0",	"710315-712612_0",	"723121-712632_0",	"710320-712632_0",	"712627-712632_0",	"712619-712632_0",	"710330-712632_0",	"712632-710324_0",	"720168-721118_0",	"716333-721118_0",	"710313-721118_0",	"712635-721118_0",	"720128-721118_0",	"721189-721118_0",	"710280-720119_0",	"712336-721118_0",	"710363-721118_0",	"712603-721118_0",	"710352-721118_0",	"710230-721118_0",	"718126-721118_0",	"718627-721118_0",	"716021-721118_0",	"711728-721118_0",	"712515-721118_0",	"712505-720119_0",	"716010-720119_0",	"716005-720119_0",	"712720-721118_0",	"722162-721118_0",	"725180-721118_0",	"713811-721118_0",	"711710-720119_0",	"718302-720119_0",	"718853-720119_0",	"716035-721118_0",	"718618-721118_0",	"721126-721118_0",	"720125-721118_0",	"716010-721118_0",	"720141-721118_0",	"718637-721118_0",	"710434-710431_2",	"710434-710322_0",	"713609-720118_0",	"718237-713609_0",	"710361-713611_0",	"713601-713617_0",	"718238-713601_0",	"713613-713601_0",	"713603-713601_1",	"713634-713601_3",	"713618-713601_0",	"713623-713609_0",	"719119-713609_0",	"713634-712745_0",	"712715-718251_0",	"712715-711408_0",	"712712-712614_0",	"712745-712620_0",	"712745-710334_0",	"712745-720163_0",	"712745-713619_0",	"712801-712624_1",	"712801-720112_0",	"712801-712722_1",	"712801-712624_0",	"712739-718407_0",	"712619-718407_0",	"716210-718407_0",	"710226-718407_1",	"712320-718407_0",	"720140-718407_0",	"721137-718407_0",	"711509-718407_0",	"712332-718407_0",	"719106-718407_0",	"716015-718407_0",	"716002-718407_0",	"716221-718407_0",	"718812-718407_0",	"720147-718407_0",	"720182-718407_0",	"718401-718407_0",	"710206-718407_0",	"716209-718407_0",	"713816-718407_0",	"710449-718407_0",	"721189-718407_0",	"718301-718407_0",	"718301-718407_1",	"712313-718407_0",	"710282-718407_0",	"711705-718407_0",	"720175-718407_0",	"720172-718407_0",	"718619-718407_0",	"710278-718407_0",	"719123-718407_0",	"721160-718407_0",	"710257-718407_1",	"710266-718407_0",	"710339-718407_0",	"710272-718407_0",	"712701-718407_0",	"710203-718407_0",	"716322-718407_0",	"718220-718407_0",	"716004-718407_0",	"710220-718407_2",	"712006-718407_0",	"718305-718407_2",	"710239-718407_1",	"718305-718407_0",	"716335-718407_0",	"718305-718407_1",	"711508-718407_0",	"710313-718407_0",	"716304-718407_0",	"710272-718407_1",	"718113-718407_0",	"720178-718407_0",	"710218-718407_0",	"718405-718407_1",	"720150-718407_0",	"720130-718407_0",	"710451-718407_0",	"718410-718407_0",	"712627-718407_0",	"712317-718407_0",	"710207-718407_0",	"716212-718407_0",	"710230-718407_0",	"716303-718407_1",	"710406-718407_0",	"710233-718407_0",	"712741-718407_0",	"710210-718407_1",	"711704-718407_0",	"716050-718407_0",	"719143-718407_0",	"716019-718407_0",	"710240-718407_1",	"716323-718407_0",	"712514-718407_0",	"712505-718407_0",	"712513-718407_0",	"710201-718406_0",	"710348-710201_0",	"710331-710201_0",	"712509-710201_0",	"721189-710201_0",	"710258-710201_0",	"718308-710201_0",	"710210-710201_0",	"711428-710201_0",	"719111-710201_0",	"718605-710201_0",	"718621-710201_0",	"718303-718406_2",	"718303-718206_0",	"718711-718303_0",	"710251-718303_0",	"710256-718303_0",	"713616-713601_0",	"719223-713601_0",	"713614-713601_1",	"713605-713601_1",	"713609-713601_1",	"713641-713601_1",	"720185-713601_0",	"710348-713601_0",	"713644-713601_0",	"712630-713601_0",	"711713-713611_0",	"713602-713601_0",	"713603-713601_2",	"721186-713601_0",	"718220-713601_0",	"713632-713608_0",	"712608-710426_0",	"710424-712610_0",	"720147-712610_0",	"718317-712616_0",	"712633-712616_0",	"712635-712608_1",	"712801-718222_0",	"712801-712726_0",	"712735-712743_0",	"712735-712727_0",	"712737-713916_0",	"712732-712735_0",	"718109-712735_0",	"710418-712735_0",	"710422-712735_0",	"712730-710402_0",	"712730-710426_0",	"712729-710311_0");
       
        Population population = scenario.getPopulation();      
        ArrayList<Id<Person>> removePers = new ArrayList<>();
        
        for (Person p : population.getPersons().values()) {
        	if(!Ids.contains(p.getId().toString())) {
        		removePers.add(p.getId());
        	}
        }
        
        for (int i = 0; i < removePers.size(); i++) {
        	population.getPersons().remove(removePers.get(i));
        }
             
        SelectedPlans2ESRIShape shp = new SelectedPlans2ESRIShape(population, network, crs, "stuck_plans");
        
        shp.setWriteActs(true);
        shp.setWriteLegs(true);
        shp.setOutputSample(1);
        shp.write(); 
	}

}
