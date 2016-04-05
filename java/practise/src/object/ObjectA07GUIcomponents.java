package object;
import javax.swing.*;
public class ObjectA07GUIcomponents {
	public static void main(String[]args){
		JButton jbtnew=new JButton("button~");
		JLabel jlblnew=new JLabel("label~");
		JTextField  jtfnew=new JTextField("textfield~");
		JCheckBox jchknewa=new JCheckBox("checkbox~a");
		JCheckBox jchknewb=new JCheckBox("checkbox~b");
		JCheckBox jchknewc=new JCheckBox("checkbox~c");
		JCheckBox jchknewd=new JCheckBox("checkbox~d");
		JRadioButton jrbnewa=new JRadioButton("radiobutton~1");
		JRadioButton jrbnewb=new JRadioButton("radiobutton~2");
		JComboBox jcbnew=new JComboBox(new String[]{"comboboxha","comboboxhei","comboboxhe","comboboxla"});
		//creat
		JPanel newpanel=new JPanel();
		newpanel.add(jbtnew);
		newpanel.add(jlblnew);
		newpanel.add(jtfnew);
		newpanel.add(jchknewa);
		newpanel.add(jchknewb);
		newpanel.add(jchknewc);
		newpanel.add(jchknewd);
		newpanel.add(jrbnewa);
		newpanel.add(jrbnewb);
		newpanel.add(jcbnew);
		//configure
		JFrame newframe=new JFrame();
		newframe.add(newpanel);
		newframe.setTitle("a show of many components");
		newframe.setSize(480,320);
		newframe.setLocation(300,300);
		newframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newframe.setVisible(true);
	}
}
