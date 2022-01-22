package game;
import static game.ChuongTrinhTracNghiem.btnCheck;
import static game.Database.connection;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class NhapDuLieu extends JFrame implements ActionListener {
	
	static JTextField txtCategory, txtQuestion, txtAnswer1, txtAnswer2, txtAnswer3, txtAnswer4,txtusername;
        static JPasswordField txtpassword;
	static JButton btnCategory,btnQuestion, openCategory, openQuestion, btnlogin,btncancel,btndelete,btnxoa;
	static JRadioButton rdAnswer1, rdAnswer2, rdAnswer3, rdAnswer4;
        static JLabel lb,lable1,lable2,lable3;
	static JComboBox cbxCategory;
	static TreeMap <String, Integer> categoryMap = new TreeMap<String, Integer>();
	static ButtonGroup group;
        static JTable jtable;
        static DefaultTableModel tableModel;
        static ResultSet result;
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Database db = new Database();
		if(arg0.getSource() == btnCategory)
		{
			JOptionPane op = new JOptionPane();
			//op.showMessageDialog(null, "1", "2", JOptionPane.ERROR_MESSAGE);
			String name = txtCategory.getText();
			String sql = "INSERT Category(name) VALUES (?)";
			
			if(db.insertCategory(sql, name))
				op.showMessageDialog(null, "Thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			else
				op.showMessageDialog(null, "Thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
		}
		
		if(arg0.getSource() == btnQuestion)
		{
			JOptionPane op = new JOptionPane();
			String question = txtQuestion.getText();
			String[] a = new String[4];
			Boolean[] s = new Boolean[4];
			if(isSelected() && !isEmpty())
			{
				a[0] = txtAnswer1.getText();
				a[1] = txtAnswer2.getText();
				a[2] = txtAnswer3.getText();
				a[3] = txtAnswer4.getText();
				s[0] = rdAnswer1.isSelected() ? true : false;
				s[1] = rdAnswer2.isSelected() ? true : false;
				s[2] = rdAnswer3.isSelected() ? true : false;
				s[3] = rdAnswer4.isSelected() ? true : false;
				String category_name = (String) cbxCategory.getSelectedItem();
				int category_id = (int) categoryMap.get(category_name);

				String sql = "INSERT question(name,category_id) VALUES(?,?)";
				
				
				int idofquestion = db.insertQuestion(sql, question, category_id);
				
				if(idofquestion > 0)
				{
					//idofquestion = db.getIDofQuestion(question);
					for(int i=0;i<a.length;i++)
					{
						db.insertAnswer(a[i], idofquestion, s[i]);
					}
					op.showMessageDialog(null, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					clearInsertQuestion();
				}else
					op.showMessageDialog(null, "Thêm thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
				
				//cbxCategory.addItem(db.getAllCategory().getString("id"));
			}else
				System.out.print("chua checked");
				
		
		}
		if(arg0.getSource() == openCategory)
		{
			new NhapDuLieu().insertCategory();
		}
		
		if(arg0.getSource() == openQuestion)
		{
			new NhapDuLieu().insertQuestion();
			ResultSet rs = db.select("SELECT * FROM category");
			db.fillComboxBox(cbxCategory, rs, categoryMap);
		}
                if(arg0.getSource() == btnlogin)
                {
                    if(txtusername.getText().equals("") || txtpassword.getText().equals("")){
                       JOptionPane.showMessageDialog(this, "username and pass is not empty!");
                    }else if(txtusername.getText().equals("admin") || txtpassword.getText().equals("admin")){
                        JOptionPane.showMessageDialog(this, "Login successfull.");
                        new NhapDuLieu().createGUI();
                    }else{
                        JOptionPane.showMessageDialog(this, "username and pass is invalid!");
                        }
                }
                if(arg0.getSource() == btncancel){
                    txtusername.setText("");
                    txtpassword.setText("");
                }
//                if(arg0.getSource() == btndelete){
//            //        new NhapDuLieu().updateData();
//			
//			
//                }
//                if(arg0.getSource() == btnxoa){
//                    new NhapDuLieu().insertQuestion();
//			ResultSet rs = db.select("SELECT * FROM category");
//			db.fillComboxBox(cbxCategory, rs, categoryMap);
//                }
	}
	
	void createGUI()
	{
		this.setSize(400, 450);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(6,6,0,0));
                
                JPanel pn = new JPanel();
                JLabel l = new JLabel();
                l.setIcon(new javax.swing.ImageIcon(getClass().getResource("gift.png")));
                 pn.add(l);
                pn.add( lb = new JLabel("TRANG QUẢN LÝ PHÂN MỀM"));
                lb.setFont(new java.awt.Font("Tahoma", 1, 18));
                JLabel l1 = new JLabel();
                l1.setIcon(new javax.swing.ImageIcon(getClass().getResource("gift.png")));
                pn.add(l1);
                
                JPanel pn2=new JPanel();
                JLabel l2 = new JLabel();
                JLabel l21 = new JLabel();
                pn2.add(l2);
		pn2.add(openCategory = new JButton("THÊM DANH MỤC"));
                pn2.add(l21);
                l2.setIcon(new javax.swing.ImageIcon(getClass().getResource("house.png")));
                l21.setIcon(new javax.swing.ImageIcon(getClass().getResource("house.png")));
                
                JPanel pn3 = new JPanel();
                JLabel l3 = new JLabel();
                JLabel l31 = new JLabel();
                l3.setIcon(new javax.swing.ImageIcon(getClass().getResource("snowman.png")));
                l31.setIcon(new javax.swing.ImageIcon(getClass().getResource("snowman.png")));
                pn3.add(l3);
                pn3.add(openQuestion = new JButton("THÊM CÂU HỎI"));
                pn3.add(l31);
                
                JPanel pn4 = new JPanel();
                JLabel l4 = new JLabel();
                l4.setIcon(new javax.swing.ImageIcon(getClass().getResource("bell.png")));
                JLabel l41 = new JLabel();
                l41.setIcon(new javax.swing.ImageIcon(getClass().getResource("bell.png")));
                JLabel l42 = new JLabel();
                l42.setIcon(new javax.swing.ImageIcon(getClass().getResource("bell.png")));
                JLabel l43 = new JLabel();
                l43.setIcon(new javax.swing.ImageIcon(getClass().getResource("noel.png")));
                JLabel l44 = new JLabel();
                l44.setIcon(new javax.swing.ImageIcon(getClass().getResource("noel.png")));
                pn4.add(l43);
                pn4.add(l4);
                pn4.add(l41);
                pn4.add(l42);
                pn4.add(l44);

                JPanel pn5 = new JPanel();
                pn5.add(btndelete = new JButton("XÓA DANH MỤC"));
                
                JPanel pn6 = new JPanel();
                pn6.add(btnxoa = new JButton("XÓA CÂU HỎI"));
                this.add(pn);
                this.add(pn4);
                this.add(pn2);
                this.add(pn3);
                this.add(pn5);
                this.add(pn6);
		openCategory.addActionListener(this);
		openQuestion.addActionListener(this);
                btndelete.addActionListener(this);
                btnxoa.addActionListener(this);
                
		this.setVisible(true);
	}
	
	void insertCategory()
	{
		setSize(300, 200);
		//setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setLayout(new GridLayout(2,2,0,0));
                JPanel p5 = new JPanel();
		p5.add(new JLabel("Nhập tên danh mục:"));
		p5.add(txtCategory = new JTextField("aaaaaaaa"));
                JPanel p6 = new JPanel();
		p6.add(btnCategory = new JButton("Thêm"));
		btnCategory.addActionListener(this);
                this.add(p5);
                this.add(p6);
		setVisible(true);
	}
	
	void insertQuestion()
	{
		setSize(400, 300);
		group = new ButtonGroup();
		group.add(rdAnswer1 = new JRadioButton("Chọn"));
		group.add(rdAnswer2 = new JRadioButton("Chọn"));
		group.add(rdAnswer3 = new JRadioButton("Chọn"));
		group.add(rdAnswer4 = new JRadioButton("Chọn"));
		
		setLayout(new GridLayout(7,2,20,20));
		add(new JLabel("Tên câu hỏi:"));
		add(txtQuestion = new JTextField());
		add(new JLabel("Chọn câu đúng"));
		add(new JLabel("Câu trả lời 1: "));
		add(txtAnswer1 = new JTextField());
		add(rdAnswer1);
		add(new JLabel("Câu trả lời 2: "));
		add(txtAnswer2 = new JTextField());
		add(rdAnswer2);
		add(new JLabel("Câu trả lời 3: "));
		add(txtAnswer3 = new JTextField());
		add(rdAnswer3);
		add(new JLabel("Câu trả lời 4: "));
		add(txtAnswer4 = new JTextField());
		add(rdAnswer4);
		
		add(btnQuestion = new JButton("Thêm"));
		add(cbxCategory = new JComboBox());
		btnQuestion.addActionListener(this);
		setVisible(true);
	}
	
	public Boolean checkValid(String str)
	{
		if(str != null && !str.isEmpty())
			return true;
		else
			return false;
	}
	
	public Boolean isEmpty()
	{
		if(txtQuestion.getText().equals("") || txtAnswer1.getText().equals("") || txtAnswer2.getText().equals("") || txtAnswer3.getText().equals("") || txtAnswer4.getText().equals(""))
			return true;
		else
			return false;
	}
	
	public Boolean isSelected()
	{
		if(rdAnswer1.isSelected() || rdAnswer2.isSelected() || rdAnswer3.isSelected() || rdAnswer4.isSelected())
		{
			return true;
		}else
			return false;
	}
	public static void main(String[] args) {
		new NhapDuLieu().loginGUI();
		
	}
	
	void clearInsertQuestion()
	{
		txtQuestion.setText("");
		txtAnswer1.setText("");
		txtAnswer2.setText("");
		txtAnswer3.setText("");
		txtAnswer4.setText("");
		group.clearSelection();
	}
   void loginGUI(){
      this.setSize(400, 380);
      setLayout(new GridLayout(4,4,0,0));
       JPanel p1 = new JPanel();
       JLabel lb =new JLabel();
       lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("tree.png")));
       p1.add(lb);
      p1.add(lable1 = new JLabel("Đăng nhập vào hệ thống"));
      lable1.setFont(new java.awt.Font("Tahoma", 1, 24));
      JPanel p2 = new JPanel();
      JLabel lb1 =new JLabel();
      lb1.setIcon(new javax.swing.ImageIcon(getClass().getResource("house.png")));
      p2.add(lb1);
      p2.add(lable2 = new JLabel("Username"));
      lable2.setFont(new java.awt.Font("Tahoma", 1, 18));
      p2.add(txtusername = new JTextField("nhập username"));
      JPanel p3 = new JPanel();
      JLabel lb2 =new JLabel();
      lb2.setIcon(new javax.swing.ImageIcon(getClass().getResource("gift.png")));
      p3.add(lb2);
      p3.add(lable3 = new JLabel("Password"));
      lable3.setFont(new java.awt.Font("Tahoma", 1, 18));
      p3.add(txtpassword = new JPasswordField("nhập password"));
      JPanel p4 = new JPanel();
      p4.add(btnlogin = new JButton("Login"));
      btnlogin.setFont(new java.awt.Font("Tahoma", 1, 20));
      btnlogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("santa.png")));
       p4.add(btncancel = new JButton("Cancel"));
       btncancel.setFont(new java.awt.Font("Tahoma", 1, 20));
       btncancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("snowman.png")));
      this.add(p1);
      this.add(p2);
      this.add(p3);
      this.add(p4);
      this.setVisible(true);
      btnlogin.addActionListener(this);
      btncancel.addActionListener(this);
      
      
   }
    
}
