package clock;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Calendar;
import java.net.*;
import java.applet.*;
class clockframe extends JFrame{
clockpanel c;
clockframe(String str){
super(str);
c=new clockpanel();
Container cpane=getContentPane();
cpane.add(c);
addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent we){
System.exit(0);
}
public void windowIconified(WindowEvent we){
//JFrame fe=new JFrame("Digital Clock");
//fe.setVisible(true);
//setVisible(true);
}
});
}
public static void main(String args[]){
clockframe cf=new clockframe("Java Clock");
cf.setSize(300,300);
cf.setLocation(975,0);
cf.setVisible(true);
}
}
class clockpanel extends JPanel implements KeyListener,ActionListener{
private Graphics gd,g;
private Image img;
private int playflag=0;
setframe sf=new setframe();
public static volatile int sec,min,hor;
Calendar cal;
private int kcode;
private double s,m,h;
public static int alarmflag=0;
clockpanel(){
cal=Calendar.getInstance();
sec=cal.get(Calendar.SECOND);
min=cal.get(Calendar.MINUTE);
hor=cal.get(Calendar.HOUR);
new Timer(1000,this).start();
addKeyListener(this);
setFocusable(true);
requestFocus();
}
public void actionPerformed(ActionEvent ae){
sec++;
if(sec==60){
sec=0;
min++;
}if(min==60){
min=0;
hor++;
if(hor>12){
hor=0;
}
}
paintscreen();
//System.out.println("hour:"+sf.h+"Min:"+sf.m);
if(min==sf.m&&hor==sf.h){
//alarmtune.play();
System.out.println("Your Time Is Over");
if(alarmflag==0){
alarmdia n=new alarmdia();
n.setSize(200,100);
n.setLocation(500,400);
n.setVisible(true);
alarmflag=1;
}
}
}
public void paintscreen(){
g=this.getGraphics();
img=createImage(300,300);
gd=img.getGraphics();
gd.setColor(Color.white);
gd.fillRect(0,0,300,300);
gd.setColor(Color.red);
gd.drawString("Current Time is:"+hor+":"+min+":"+sec,10,10);
gd.setColor(Color.blue);
gd.drawOval(20,10,250,250);
gd.fillRect(145,135,4,4);
gd.drawString("12",135,20);
gd.drawString("3",262,142);
gd.drawString("6",141,260);
gd.drawString("9",21,142);
gd.setColor(Color.black);
paintsec(gd);
paintmin(gd);
painthor(gd);
g.drawImage(img,0,0,null);
//System.out.println("Completes");
}
public void keyPressed(KeyEvent ke){
kcode=ke.getKeyCode();
if(kcode==ke.VK_A){
setframe f=new setframe("Clock Conrol Panel");
f.setVisible(true);
f.setLocation(975,300);
f.setSize(300,140);
}
}
public void keyTyped(KeyEvent ke){}
public void keyReleased(KeyEvent ke){}
public void paintsec(Graphics g){
s=Math.toRadians(sec*6-90);
g.drawLine(145,138,(int)(100*Math.cos(s))+145,(int)(100*Math.sin(s))+135);
}
public void paintmin(Graphics g){
m=Math.toRadians(min*6-90);
g.drawLine(145,138,(int)(85*Math.cos(m))+145,(int)(85*Math.sin(m))+135);
}
public void painthor(Graphics g){
h=Math.toRadians(hor*30+min*.5-90);
g.drawLine(145,138,(int)(60*Math.cos(h))+145,(int)(60*Math.sin(h))+135);
}
}
class setframe extends JFrame implements ActionListener{
clockpanel cp;
JButton b1,b2,b3;
JTextField t;
JLabel l;
public static int h,m;
public String alarm;
setframe(){}
setframe(String str){
super(str);
Container cpane=getContentPane();
cpane.setLayout(new FlowLayout());
JButton b1=new JButton("Set Time"); 
JButton b2=new JButton("Set Alarm"); 
JButton b3=new JButton("Digital");
t=new JTextField(20);
l=new JLabel("Designed By-Rajat Saxena,CS-3rd Year");
b1.addActionListener(this); 
b2.addActionListener(this); 
b3.addActionListener(this); 
//cpane.add(b1);
cpane.add(b2);
//cpane.add(b3);
cpane.add(t);
cpane.add(l);
cp=new clockpanel();
}
public void actionPerformed(ActionEvent ae){
if(ae.getActionCommand()=="Set Alarm"){
alarm=t.getText();
cp.alarmflag=0;
System.out.println(alarm);
h=Integer.parseInt(alarm.substring(0,2));
System.out.println("H:"+h);
m=Integer.parseInt(alarm.substring(3,5));
System.out.println("M:"+m);
}
}
}
class alarmdia extends JDialog{
JLabel l;
alarmdia(){
l=new JLabel("Your Time Is Over");
Container cpane=getContentPane();
cpane.setLayout(new BorderLayout());
cpane.add(l,BorderLayout.CENTER);
addWindowListener(new WindowAdapter(){
public void windowClosed(WindowEvent we){
setVisible(false);
}
});
}
}