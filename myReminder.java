import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.Timer;
/**
 * 
 * @author needle
 * 2014年 04月 29日 星期二 15:51:19 CST
 */
public class myReminder extends JFrame{
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private JLabel text;
	private JLabel textLabel;
	private JTextField textEdit;
	private JLabel timeLabel;
	private JTextField timeEdit;
	private JButton change;
	private JButton ok;
	private static long minute;
	private static String show;
	public myReminder(){
		timer=new Timer();
		minute=60;
		initComponent();
	}
	public void initComponent(){
		show="do something...";
		text=new JLabel("interval "+minute+"(min):"+show);
		textLabel=new JLabel("do what? :");
		textEdit=new JTextField("");
		timeLabel=new JLabel("interval(min):");
		timeEdit=new JTextField("30");
		change=new JButton("update");
		ok=new JButton("run");
		JPanel centerPanel1=new JPanel();
		centerPanel1.setLayout(new GridLayout(1,2));
		JPanel centerPanel2=new JPanel();
		centerPanel2.setLayout(new GridLayout(1,2));
		JPanel bottonPanel=new JPanel();
		bottonPanel.setLayout(new GridLayout(1,2));
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(4,1));
		this.setTitle("Reminder!");
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		
		change.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				changePerformed(e);
			}
		});
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				okPerformed(e);
			}
		});
		
		centerPanel1.add(textLabel);
		centerPanel1.add(textEdit);
		centerPanel2.add(timeLabel);
		centerPanel2.add(timeEdit);
		bottonPanel.add(change);
		bottonPanel.add(ok);
		this.add(text);
		this.add(centerPanel1);
		this.add(centerPanel2);
		this.add(bottonPanel);
		pack();
	}
	
	private void changePerformed(ActionEvent e){	
		if(!timeEdit.getText().isEmpty()){
			minute=Long.parseLong(timeEdit.getText());
		}
		if(!textEdit.getText().isEmpty()){
			show=textEdit.getText();	
		}
		text.setText("interval"+minute+"(min):"+show);
		pack();
	}
	private void okPerformed(ActionEvent e){
		timer.cancel();
		timer=new Timer();
		timer.schedule(new RemindTask(),minute*60*1000);
		this.setVisible(false);
	}
	private class RemindTask extends TimerTask{
		public void run() {
			runPerformed();
		}
	}
	private void runPerformed(){
		textEdit.setText("");
		timeEdit.setText("30");
		this.setVisible(true);
	}
	public static void main(String[] args){
		myReminder frame=new myReminder();
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if(frameSize.height > screenSize.height){
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width=screenSize.width;
		}
		frame.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
		frame.setVisible(true);
	}
}

