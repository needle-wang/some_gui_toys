import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Calculator extends JPanel {
	private JButton[] x = new JButton[10];
	private JButton[] y = new JButton[7];
	//private int i;
	JTextField t = new JTextField();
	JPanel p = new JPanel();
	String tmp1="";
	double tmp;
	int fuhao;
	public Calculator() {
		this.setLayout(new BorderLayout());
		p.setLayout(new GridLayout(5, 4));
		y[0]=new JButton(".");
		y[1]=new JButton("＝");
		y[2]=new JButton("＋");
		y[3]=new JButton("－");
		y[4]=new JButton("＊");
		y[5]=new JButton("／");
		y[6]=new JButton("Ｃ");
		for (int i = 0; i < 10; i++) {
			x[i] = new JButton(Integer.toString(i));
			p.add(x[i]);
		}
		for(int i=0;i<7;i++){
			p.add(y[i]);
		}
		x[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(tmp1!=""){
					tmp1=tmp1+"0";
					t.setText(tmp1);
				}
			}});
		x[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				tmp1=tmp1+"1";
				t.setText(tmp1);
			}});
		x[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				tmp1=tmp1+"2";
				t.setText(tmp1);
			}});
		x[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				tmp1=tmp1+"3";
				t.setText(tmp1);
			}});
		x[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				tmp1=tmp1+"4";
				t.setText(tmp1);
			}});
		x[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				tmp1=tmp1+"5";
				t.setText(tmp1);
			}});
		x[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				tmp1=tmp1+"6";
				t.setText(tmp1);
			}});
		x[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				tmp1=tmp1+"7";
				t.setText(tmp1);
			}});
		x[8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				tmp1=tmp1+"8";
				t.setText(tmp1);
			}});
		x[9].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				tmp1=tmp1+"9";
				t.setText(tmp1);
			}});
		
		y[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				char[] x=tmp1.toCharArray();
				boolean a=false;
				for(int i=0;i<x.length;i++){
					if(x[i]=='.'){
						a=true;
					}
				}
				if(!a){
					tmp1=tmp1+".";
					t.setText(tmp1);
				}
			}});
		//***********关键************
		y[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(fuhao==1){
					if(tmp1!=""){
						tmp=Double.valueOf(tmp1)+tmp;
					t.setText(""+tmp);}
				}
				if(fuhao==2){
					if(tmp1!=""){
						tmp=tmp-Double.valueOf(tmp1);
					t.setText(""+tmp);}
				}
				if(fuhao==3){
					if(tmp1!=""){
					tmp=(Double.valueOf(tmp1)*tmp);
					t.setText(""+tmp);}
				}
				if(fuhao==4){
					if(tmp1!=""){
					tmp=(tmp/Double.valueOf(tmp1));
					t.setText(""+tmp);}
				}
				tmp1="";
			}});
		//加减乘除
		y[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				fuhao=1;
				if(tmp1!=""){
				tmp=Double.valueOf(tmp1);
				tmp1="";}
			}});
		y[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				fuhao=2;
				if(tmp1!=""){
				tmp=Double.valueOf(tmp1);
				tmp1="";}
			}});
		y[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				fuhao=3;
				if(tmp1!=""){
				tmp=Double.valueOf(tmp1);
				tmp1="";}
			}});
		y[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				fuhao=4;
				if(tmp1!=""){
				tmp=Double.valueOf(tmp1);
				tmp1="";}
			}});
		y[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				tmp1="";
				tmp=0;
				t.setText(tmp1);
			}});

		this.add(p);
		t.setEditable(false);
		this.add(t, BorderLayout.NORTH);

	}

	public static void main(String[] args) {
		JFrame f = new JFrame("Calculator");
		Calculator c = new Calculator();
		f.add(c);
		f.setSize(500, 300);
		f.setLocation(500, 200);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}

}
