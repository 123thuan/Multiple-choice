
package game;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;

public class ChuongTrinhTracNghiem extends JFrame implements ActionListener {
	
	static JTextField txtCategory, txtQuestion, txtAnswer1, txtAnswer2, txtAnswer3, txtAnswer4;
	static JRadioButton rdAnswer1, rdAnswer2, rdAnswer3, rdAnswer4;
	static JButton btnStart, btnStop, btnNext, btnCheck, btnTimer;
	ActionListener actionToPreform;
	static int numRand = 5, currentQuestion = 0;
	static JPanel p1,p2,p3,p4;
	static int[] ListQuestionID = new int[numRand];
	static String[] ListQuestionName = new String[numRand];
	static int[] ListAnswer = new int[numRand];
	static ButtonGroup group;
	static JComboBox cbxCategory;
	static TreeMap <String, Integer> categoryMap = new TreeMap<String, Integer>();
	static JOptionPane op = new JOptionPane();
	static JLabel lblCountDown;
	//static String[] ListAnswerOfQuestion = new String[4];
	Timer countdown;
	static int minutes, seconds, time, delay = 1000;
	Database db = new Database();
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == btnStart)
		{
			start();
		}
		
		if(arg0.getSource() == btnCheck)
		{
			int abc = checkResult(ListQuestionID, ListAnswer);
			op.showMessageDialog(null, "Số câu đúng là: "+ abc, "Kết quả", JOptionPane.INFORMATION_MESSAGE);
			System.out.print("So cau dung: "+abc);
		}
		
		if(arg0.getSource() == btnNext)
		{
			
			if(rdAnswer1.isSelected())
				ListAnswer[currentQuestion-1] = Integer.parseInt(rdAnswer1.getName());
			if(rdAnswer2.isSelected())
				ListAnswer[currentQuestion-1] = Integer.parseInt(rdAnswer2.getName());
			if(rdAnswer3.isSelected())
				ListAnswer[currentQuestion-1] = Integer.parseInt(rdAnswer3.getName());
			if(rdAnswer4.isSelected())
				ListAnswer[currentQuestion-1] = Integer.parseInt(rdAnswer4.getName());
			if(currentQuestion < numRand)
				next();
			else
			{
				countdown.stop();
				int abc = checkResult(ListQuestionID, ListAnswer);
				op.showMessageDialog(null, "Hoàn thành trước thời gian, thời gian còn lại là "+minutes+" phút "+seconds+" giầy. Số câu đúng là: "+ abc, "Kết quả", JOptionPane.INFORMATION_MESSAGE);
				System.out.print("So cau dung: "+abc);
			}
		}
               if(arg0.getSource() == btnStop)
		{
		   countdown.stop();
                    int abc = checkResult(ListQuestionID, ListAnswer);
		    op.showMessageDialog(null, "kết thúc bài làm!! Số câu đúng là: "+ abc, "Kết quả", JOptionPane.INFORMATION_MESSAGE);
		    System.out.print("So cau dung: "+abc);
				
		}
		
		if(arg0.getSource() == countdown)
		{
			minutes = time / 60;
			seconds = time % 60;
			System.out.println("Phút: "+minutes+" Giây "+seconds);
			time = time - 1;
			lblCountDown.setText(minutes+" phút "+seconds+" giây");
			
			if(time == 0)
			{
				countdown.stop();
				lblCountDown.setText("Hết giờ");
				int abc = checkResult(ListQuestionID, ListAnswer);
				op.showMessageDialog(null, "Hết giờ làm bài!! Số câu đúng là: "+ abc, "Kết quả", JOptionPane.INFORMATION_MESSAGE);
				System.out.print("So cau dung: "+abc);
			}
			p1.revalidate();
			p1.repaint();
			//else
				//seconds++;
		}
		
	}
	
	void showListAnswer()
	{
		System.out.println("Danh sach tra loi: ");
		for(int i = 0 ;i< ListAnswer.length; i++)
		{
			System.out.print(ListAnswer[i]);
		}
	}
	void createGUI()
	{
               
		this.setSize(350, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(4,4,0,0));
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.setTitle("Chương trình trắc nghiệm");
		p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		p1.add(btnStart = new JButton("Bắt đầu"));
                  btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("access.png"))); 
		p1.add(btnStop = new JButton("Kết thúc"));
                 btnStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("Select.png")));
		p1.add(btnCheck = new JButton("Kiểm tra"));
                 btnCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("employee.png")));
                
	
                
		
		
		p1.setLayout(new FlowLayout());
                JLabel lable = new JLabel();
                lable.setIcon(new javax.swing.ImageIcon(getClass().getResource("btn_add.png"))); 
                p1.add(lable);
                        
		p1.add(new JLabel("Chọn lĩnh vực: "));
		
		p1.add(cbxCategory = new JComboBox());
		p1.add(lblCountDown = new JLabel());
		ResultSet rs = db.select("SELECT * FROM category");
		db.fillComboxBox(cbxCategory, rs, categoryMap);
                JLabel lable1 = new JLabel();
                lable1.setIcon(new javax.swing.ImageIcon(getClass().getResource("btn_add.png"))); 
                p1.add(lable1);
		p3 = new JPanel();
		p3.setLayout(new GridLayout(5,1));
                p4 = new JPanel();
                JLabel lablel1 = new JLabel();
                lablel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("btn_add.png")));
                p4.add(lablel1);
                p4.add(btnNext = new JButton("Tiếp theo"));
                JLabel lablel2 = new JLabel();
                lablel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("btn_add.png")));
                p4.add(lablel2);
                p2 = new JPanel();
                JLabel lable2 = new JLabel();
                lable2.setIcon(new javax.swing.ImageIcon(getClass().getResource("rating.png"))); 
                p2.add(lable2);
//		
		this.add(p1);
		this.add(p3);
                this.add(p4);
                this.add(p2);
		btnStart.addActionListener(this);
		btnCheck.addActionListener(this);
		btnNext.addActionListener(this);
                btnStop.addActionListener(this);
		this.setVisible(true);
	}
	
	void start()
	{
            
		currentQuestion = 0;

		String category_name = (String)cbxCategory.getSelectedItem();
		int category_id = (int) categoryMap.get(category_name);
		int count = 10; //Integer count is set equal to 30.
		int delay = 1000; 
		time = numRand * 12;
		countdown = new Timer(delay, this);
		countdown.start();
		//countdown();
		
		getListQuestion(category_id);
		next();
		this.setVisible(true);
	}
	
	void next()
	{
		//p2.removeAll();
		
		//getListAnswerOfQuestion(ListQuestionID[0]);
		group = new ButtonGroup();
		if(currentQuestion < ListQuestionID.length)
		{
			System.out.print("cau hoi: "+ListQuestionID[currentQuestion]);
			System.out.println(currentQuestion);
			ResultSet rs = getListAnswerOfQuestion(ListQuestionID[currentQuestion]);
			int i = 0;
			String name, id;
			p3.removeAll();
			p3.revalidate();
			p3.repaint();

			try {
				p3.add(new JLabel("câu hỏi: "+ListQuestionName[currentQuestion]));
				while(rs.next())
				{
					name = rs.getString("name");
					id = Integer.toString(rs.getInt("id"));
					System.out.print("Label: "+name);
					System.out.println("- Background: "+id);
					if(i == 0)
					{
						rdAnswer1 = new JRadioButton("A:  "+name);
						group.add(rdAnswer1);
						rdAnswer1.setName(id);
						p3.add(rdAnswer1);
					}
					if(i == 1)
					{
						group.add(rdAnswer2 = new JRadioButton("B:  "+name));
						rdAnswer2.setName(id);
						p3.add(rdAnswer2);
					}
					if(i == 2)
					{
						group.add(rdAnswer3 = new JRadioButton("C:  "+name));
						rdAnswer3.setName(id);
						p3.add(rdAnswer3);
					}
					if(i == 3)
					{
						group.add(rdAnswer4 = new JRadioButton("D:  "+name));
						rdAnswer4.setName(id);
						p3.add(rdAnswer4);
					}
					i++;
					
				}
				currentQuestion++;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	void getListQuestion(int id)
	{
		ResultSet rs = db.select("SELECT id,name from Question WHERE category_id = "+id+" ORDER BY RAND() LIMIT "+numRand);
		int i = 0;
		try {
			System.out.print("danh sach cau hoi: ");
			while(rs.next())
			{
				ListQuestionID[i] = (int)rs.getInt("id");
				ListQuestionName[i] = rs.getString("name");
				System.out.print(ListQuestionID[i]);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	ResultSet getListAnswerOfQuestion(int id)
	{
		return db.select("SELECT id,name from Answer where question_id = "+id+" ORDER BY RAND() LIMIT 4");
	}
	
	int checkResult(int[] lq, int[] la)
	{
		int count = 0;
		String sql = "SELECT COUNT(*) as COUNT FROM question_answer WHERE ";
		for(int i = 0;i< la.length;i++)
		{
			sql += "(question_id ="+lq[i]+" AND answer_id = "+la[i]+")";
			if((i + 1) < lq.length)
			{
				sql += " OR ";
			}
		}
		System.out.println("sql: "+ sql);
		
		ResultSet rs = db.select(sql);
		try {
			while(rs.next())
			{
				count = rs.getInt("COUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	public static void main(String[] args) {
		new ChuongTrinhTracNghiem().createGUI();
		
	}

}
