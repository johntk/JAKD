package db;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

import oracle.jdbc.pool.OracleDataSource;

public class DBsql extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel top,bottom;
	private JScrollPane js;
	private JTextArea ta;
	
	private String passw,tallaghtDB,localDB;
	private JButton connect;
	private JLabel selection,urlText,uName,pass,empty;
	private JTextField username,url;
	private JPasswordField pswd;
	private JComboBox<String> jcb;
	private GridBagConstraints gc;
	
	private Connection conn;
	private Statement stmt;
	
	public DBsql()
	{
		frame = new JFrame();
		frame.setLayout(new GridLayout(2,1));
		frame.setTitle("Database Script");
		frame.setSize(550,600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gc = new GridBagConstraints();
		tallaghtDB = "jdbc:oracle:thin:@//10.10.2.7:1521/global1";
		localDB = "jdbc:oracle:thin:HR/@localhost:1521:XE";
		
		
		
		top = new JPanel(new GridBagLayout());
		top.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
		String[] options = {"Tallaght Database","Local Database"};
		
		selection = new JLabel("Select a Database Connection:");
		gc.gridx =0;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		top.add(selection,gc);
		
		jcb = new JComboBox<>(options);
		jcb.addActionListener(this);
		gc.gridx =0;
		gc.gridy =1;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		top.add(jcb,gc);
		
		urlText = new JLabel("URL:");
		gc.gridx =0;
		gc.gridy =2;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		top.add(urlText,gc);
		
		url = new JTextField(25);
		url.setEditable(false);
		url.setText(tallaghtDB);
		gc.gridx =1;
		gc.gridy =2;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		top.add(url,gc);
		
		empty = new JLabel("               ");
		gc.gridx =0;
		gc.gridy =3;
		gc.weightx=1.0;
		gc.weighty=1.0;
		top.add(empty,gc);
		
		uName = new JLabel("Username: ");
		gc.gridx =0;
		gc.gridy =4;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		top.add(uName,gc);
		
		username = new JTextField(25);
		gc.gridx =1;
		gc.gridy =4;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		top.add(username,gc);
		
		pass = new JLabel("Password: ");
		gc.gridx =0;
		gc.gridy =5;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		top.add(pass,gc);
		
		pswd = new JPasswordField(25);
		gc.gridx =1;
		gc.gridy =5;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		top.add(pswd,gc);
		
		connect = new JButton("Connect");
		connect.addActionListener(this);
		gc.gridx =0;
		gc.gridy =6;
		gc.weightx=1.0;
		gc.weighty=1.0;
		top.add(connect,gc);
		
		frame.add(top);
		
		
		
		bottom = new JPanel();
		js = new JScrollPane();
		js.setPreferredSize(new Dimension(400,250));
		ta = new JTextArea(15,40);
		js.add(ta);
		bottom.add(ta);
		frame.add(bottom);
		
		frame.setVisible(true);
	}

	public void dropTables()
	{
		ta.append("Checking for existing tables.\n");
		try
		{
			stmt = conn.createStatement();
			stmt.execute("DROP TABLE console");
			stmt.execute("DROP TABLE sound_dock");

			stmt.execute("DROP TABLE headphones");
			stmt.execute("DROP TABLE cd_artist");
			stmt.execute("DROP TABLE song");
			stmt.execute("DROP TABLE artist");
			stmt.execute("DROP TABLE game");
			stmt.execute("DROP TABLE cd");
			stmt.execute("DROP TABLE dvd");
			stmt.execute("DROP TABLE electronic");
			stmt.execute("DROP TABLE digital_product");
			stmt.execute("DROP TABLE transaction");
			stmt.execute("DROP TABLE product");
			stmt.execute("DROP TABLE employee");
			stmt.execute("DROP SEQUENCE transaction_seq");
			stmt.execute("DROP SEQUENCE headphones_seq");
			stmt.execute("DROP SEQUENCE sound_dock_seq");
			stmt.execute("DROP SEQUENCE console_seq");
			stmt.execute("DROP SEQUENCE artist_seq");
			stmt.execute("DROP SEQUENCE song_seq");
			stmt.execute("DROP SEQUENCE cd_seq");
			stmt.execute("DROP SEQUENCE game_seq");
			stmt.execute("DROP SEQUENCE dvd_seq");
			stmt.execute("DROP SEQUENCE elec_seq");
			stmt.execute("DROP SEQUENCE digi_seq");
			stmt.execute("DROP SEQUENCE prod_seq");
			stmt.execute("DROP SEQUENCE empId_seq");
			ta.append("All tables successfully dropped.\n");
		} catch (SQLException ex)
		{
			ta.append("ERROR");
		}
	}

	public void createSequence()
	{
		ta.append("\nCreating sequences...");
		try
		{
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("create sequence prod_seq start with 27 increment by 1");
			stmt.executeUpdate("create sequence empId_seq start with 5679 increment by 1");
			stmt.executeUpdate("create sequence digi_seq start with 18 increment by 1");
			stmt.executeUpdate("create sequence elec_seq start with 10 increment by 1");
			stmt.executeUpdate("create sequence dvd_seq start with 6 increment by 1");
			stmt.executeUpdate("create sequence game_seq start with 5 increment by 1");
			stmt.executeUpdate("create sequence cd_seq start with 9 increment by 1");
			stmt.executeUpdate("create sequence song_seq start with 106 increment by 1");
			stmt.executeUpdate("create sequence artist_seq start with 9 increment by 1");
			stmt.executeUpdate("create sequence console_seq start with 4 increment by 1");
			stmt.executeUpdate("create sequence sound_dock_seq start with 3 increment by 1");
			stmt.executeUpdate("create sequence headphones_seq start with 5 increment by 1");
			stmt.executeUpdate("create sequence transaction_seq start with 1000000 increment by 1");
			ta.append("Sequences created successfully.");
		} catch (SQLException ex)
		{
			ta.append("ERROR: " + ex.getMessage());
		}
	}

	public void createTables()
	{
		ta.append("\nCreating tables...");
		try
		{
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("Create table EMPLOYEE (emp_id NUMBER NOT NULL, f_name VARCHAR2(30), l_name VARCHAR2(30), house_number VARCHAR2(5), street VARCHAR2(30), town VARCHAR2(30), city VARCHAR2(30), pps_num VARCHAR2(30), pin_num VARCHAR2(30), manager VARCHAR2(1) CHECK(manager in ('Y', 'N')), PRIMARY KEY (emp_id))");
			stmt.executeUpdate("Create table PRODUCT (prod_id VARCHAR2(50), prod_type VARCHAR2(20), current_stock NUMBER, PRIMARY KEY (prod_id))");
			stmt.executeUpdate("Create table TRANSACTION (trans_id NUMBER NOT NULL, trans_date DATE, trans_type VARCHAR2(1) CHECK(trans_type IN('S','R')), total_cost NUMBER(30,2), quantity NUMBER, emp_id number, prod_id VARCHAR2(50), PRIMARY KEY (trans_id, prod_id), FOREIGN KEY (emp_id) REFERENCES EMPLOYEE(emp_id), FOREIGN KEY (prod_id) REFERENCES PRODUCT(prod_id))");
			stmt.executeUpdate("Create table DIGITAL_PRODUCT (dig_id VARCHAR2(50), genre	VARCHAR2(30), age_rating NUMBER, prod_id	VARCHAR2(50), PRIMARY KEY (dig_id), FOREIGN KEY (prod_id) REFERENCES PRODUCT(prod_id))");
			stmt.executeUpdate("Create table ELECTRONIC (elec_id	VARCHAR2(50), manufacturer VARCHAR2(30), model VARCHAR2(30), colour VARCHAR2(30), prod_id VARCHAR2(50), PRIMARY KEY (elec_id), FOREIGN KEY (prod_id) REFERENCES PRODUCT(prod_id))");
			stmt.executeUpdate("Create table DVD (dvd_id VARCHAR2(50), dvd_length NUMBER, dvd_name VARCHAR2(100), studio VARCHAR2(30), dvd_cost_price NUMBER(6,2), dvd_sale_price NUMBER(6,2), dig_id VARCHAR2(50), PRIMARY KEY (dvd_id), FOREIGN KEY (dig_id) REFERENCES DIGITAL_PRODUCT(dig_id))");
			stmt.executeUpdate("Create table CD (cd_id VARCHAR2(50), album_length NUMBER, album_name VARCHAR2(30), record_company VARCHAR2(30), cd_cost_price NUMBER(6,2), cd_sale_price NUMBER(6,2), dig_id VARCHAR2(50), PRIMARY KEY (cd_id), FOREIGN KEY (dig_id) REFERENCES DIGITAL_PRODUCT(dig_id))");
			stmt.executeUpdate("Create table GAME(game_id VARCHAR2(50), company VARCHAR2(30), platform VARCHAR2(30), game_name VARCHAR2(100), game_cost_price NUMBER(6,2), game_sale_price NUMBER(6,2), dig_id VARCHAR2(50), PRIMARY KEY (game_id), FOREIGN KEY (dig_id) REFERENCES DIGITAL_PRODUCT(dig_id))");
			stmt.executeUpdate("Create table ARTIST (artist_id VARCHAR2(50), artist_name VARCHAR2(30), PRIMARY KEY (artist_id))");
			stmt.executeUpdate("Create table SONG (song_id VARCHAR2(50), song_length VARCHAR2(10), song_name VARCHAR2(100), cd_id VARCHAR2(50), PRIMARY KEY (song_id), FOREIGN KEY (cd_id) REFERENCES CD(cd_id))");
			stmt.executeUpdate("Create table CD_ARTIST (cd_id VARCHAR2(50), artist_id VARCHAR2(50), PRIMARY KEY (cd_id,artist_id), FOREIGN KEY (cd_id) REFERENCES CD(cd_id), FOREIGN KEY (artist_id) REFERENCES ARTIST(artist_id))");
			stmt.executeUpdate("Create table HEADPHONES (headphone_id VARCHAR2(50), over_ear VARCHAR2(1) CHECK(over_ear IN('Y','N')), microphone VARCHAR2(1) CHECK(microphone IN('Y','N')), iphone_compatible VARCHAR2(1) CHECK(iphone_compatible IN('Y','N')), headphone_cost_price NUMBER(6,2), headphone_sale_price NUMBER(6,2), elec_id VARCHAR2(50), PRIMARY KEY (headphone_id), FOREIGN KEY (elec_id) REFERENCES ELECTRONIC(elec_id))");
			stmt.executeUpdate("Create table SOUND_DOCK (sd_id VARCHAR2(50), wireless VARCHAR2(1) CHECK(wireless IN('Y','N')), power_ouput NUMBER, digital_radio VARCHAR2(1) CHECK(digital_radio IN('Y','N')), sd_cost_price NUMBER(6,2), sd_sale_price NUMBER(6,2), elec_id VARCHAR2(50), PRIMARY KEY (sd_id), FOREIGN KEY (elec_id) REFERENCES ELECTRONIC(elec_id))");
			stmt.executeUpdate("Create table CONSOLE (console_id VARCHAR2(50), storage_size NUMBER, wifi VARCHAR2(1) CHECK(wifi IN('Y','N')), num_controllers NUMBER, console_cost_price NUMBER(6,2), console_sale_price NUMBER(6,2), elec_id VARCHAR2(50), PRIMARY KEY (console_id), FOREIGN KEY (elec_id) REFERENCES ELECTRONIC(elec_id))");
			ta.append("Tables created successfully.");
		} catch (SQLException ex)
		{
			ta.append("ERROR: " + ex.getMessage());
		}
	}

	public void inserts()
	{
		ta.append("\nInserting data...");
		try
		{
//			Insert values into product table
			Statement stmt = conn.createStatement();
			stmt.execute("insert into PRODUCT values('P0000001','CD',15)");
			stmt.execute("insert into PRODUCT values('P0000002','CD',10)");
			stmt.execute("insert into PRODUCT values('P0000003','CD',5)");
			stmt.execute("insert into PRODUCT values('P0000004','CD',25)");
			stmt.execute("insert into PRODUCT values('P0000005','CD',12)");
			stmt.execute("insert into PRODUCT values('P0000006','CD',30)");
			stmt.execute("insert into PRODUCT values('P0000007','CD',16)");
			stmt.execute("insert into PRODUCT values('P0000008','CD',30)");
			stmt.execute("insert into PRODUCT values('P0000009','DVD',30)");
			stmt.execute("insert into PRODUCT values('P0000010','DVD',50)");
			stmt.execute("insert into PRODUCT values('P0000011','DVD',15)");
			stmt.execute("insert into PRODUCT values('P0000012','DVD',16)");
			stmt.execute("insert into PRODUCT values('P0000013','DVD',8)");
			stmt.execute("insert into PRODUCT values('P0000014','GAME',25)");
			stmt.execute("insert into PRODUCT values('P0000015','GAME',35)");
			stmt.execute("insert into PRODUCT values('P0000016','GAME',7)");
			stmt.execute("insert into PRODUCT values('P0000017','GAME',40)");
			stmt.execute("insert into PRODUCT values('P0000018','CONSOLE',10)");
			stmt.execute("insert into PRODUCT values('P0000019','CONSOLE',10)");
			stmt.execute("insert into PRODUCT values('P0000020','HEADPHONES',15)");
			stmt.execute("insert into PRODUCT values('P0000021','HEADPHONES',20)");
			stmt.execute("insert into PRODUCT values('P0000022','HEADPHONES',12)");
			stmt.execute("insert into PRODUCT values('P0000023','HEADPHONES',8)");
			stmt.execute("insert into PRODUCT values('P0000024','SOUNDDOCK',3)");
			stmt.execute("insert into PRODUCT values('P0000025','SOUNDDOCK',5)");
			stmt.execute("insert into PRODUCT values('P0000026','CONSOLE',8)");
			stmt.execute("insert into PRODUCT values('P0000027','CONSOLE',50)");
			stmt.execute("insert into PRODUCT values('P0000028','GAME',10)");
			stmt.execute("insert into PRODUCT values('P0000029','GAME',10)");
			stmt.execute("insert into PRODUCT values('P0000030','GAME',5)");
			stmt.execute("insert into PRODUCT values('P0000031','GAME',8)");
			
			
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000001','Rock',12,'P0000001')");
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000002','Electronica',0,'P0000002')");
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000003','Soul',0,'P0000003')");
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000004','Pop',0,'P0000004')");
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000005','Rock',12,'P0000005')");
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000006','Pop',12,'P0000006')");
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000007','Rock',12,'P0000007')");
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000008','Progressive Metal',12,'P0000008')");
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000009','Thriller',13,'P0000009')");
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000010','Drama',13,'P0000010')");
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000011','Science Fiction',13,'P0000011')");
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000012','Action',13,'P0000012')");
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000013','Fantasy',13,'P0000013')");
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000014','action-adventure',18,'P0000014')");
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000015','Action',18,'P0000015')");
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000016','Free-roaming',18,'P0000016')");
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000017','Action',18,'P0000017')");
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000018','Action',18,'P0000028')");
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000019','Action',18,'P0000029')");
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000020','Adventure',0,'P0000030')");
			stmt.execute("insert into DIGITAL_PRODUCT values('D0000021','Adventure',0,'P0000031')");
			
			
			stmt.execute("insert into ELECTRONIC values('E0000001','Sony','Playstation 4','Black','P0000018')");
			stmt.execute("insert into ELECTRONIC values('E0000002','Microsoft','Xbox 360','Black','P0000019')");
			stmt.execute("insert into ELECTRONIC values('E0000003','Beats','Wireless Over-Ear Headphones','Black','P0000020')");
			stmt.execute("insert into ELECTRONIC values('E0000004','Bose','AE2','White','P0000021')");
			stmt.execute("insert into ELECTRONIC values('E0000005','Sennheiser','CX 300 II','Black','P0000022')");
			stmt.execute("insert into ELECTRONIC values('E0000006','Sony','MDREX10LP','Black','P0000023')");
			stmt.execute("insert into ELECTRONIC values('E0000007','Bose','SoundDock II','Black','P0000024')");
			stmt.execute("insert into ELECTRONIC values('E0000008','Samsung','DA-E750','Red','P0000025')");
			stmt.execute("insert into ELECTRONIC values('E0000009','Nintendo','Wii U','Black','P0000026')");
			stmt.execute("insert into ELECTRONIC values('E0000010','Microsoft','Xbox One','Black','P0000027')");
			
			
			stmt.execute("insert into CD values('C0000001',68,'AM','Domino',9.50,16.99,'D0000001')");
			stmt.execute("insert into CD values('C0000002',72,'Random Access Memories','Columbia',10.50,18.99,'D0000002')");
			stmt.execute("insert into CD values('C0000003',75,'Love in the Future','Columbia',9.49,17.99,'D0000003')");
			stmt.execute("insert into CD values('C0000004',50,'Pure Heroine','Motown/Universal',10.48,19.99,'D0000004')");
			stmt.execute("insert into CD values('C0000005',65,'Your Arsenal','Rhino Records',10.40,19.99,'D0000005')");
			stmt.execute("insert into CD values('C0000006',53,'Native','Interscope Records',9.60,15.99,'D0000006')");
			stmt.execute("insert into CD values('C0000007',49,'Abbey Road','EMI',6.49,11.99,'D0000007')");
			stmt.execute("insert into CD values('C0000008',59,'Aenima','Volcano',8.50,16.99,'D0000008')");
			
			
			stmt.execute("insert into ARTIST values('A0000001','Arctic Monkeys')");
			stmt.execute("insert into ARTIST values('A0000002','Daft Punk')");
			stmt.execute("insert into ARTIST values('A0000003','John Legend')");
			stmt.execute("insert into ARTIST values('A0000004','Lorde')");
			stmt.execute("insert into ARTIST values('A0000005','Morrissey')");
			stmt.execute("insert into ARTIST values('A0000006','One Republic')");
			stmt.execute("insert into ARTIST values('A0000007','The Beatles')");
			stmt.execute("insert into ARTIST values('A0000008','Tool')");
			
			
			stmt.execute("insert into CD_ARTIST values('C0000001','A0000001')");
			stmt.execute("insert into CD_ARTIST values('C0000002','A0000002')");
			stmt.execute("insert into CD_ARTIST values('C0000003','A0000003')");
			stmt.execute("insert into CD_ARTIST values('C0000004','A0000004')");
			stmt.execute("insert into CD_ARTIST values('C0000005','A0000005')");
			stmt.execute("insert into CD_ARTIST values('C0000006','A0000006')");
			stmt.execute("insert into CD_ARTIST values('C0000007','A0000007')");
			stmt.execute("insert into CD_ARTIST values('C0000008','A0000008')");
			
			
			stmt.execute("insert into SONG values('S0000001','3:12','Do I Wanna Know?','C0000001')");
			stmt.execute("insert into SONG values('S0000002','4:12','R U Mine?','C0000001')");
			stmt.execute("insert into SONG values('S0000003','4:02','One For The Road','C0000001')");
			stmt.execute("insert into SONG values('S0000004','3:58','Arabella','C0000001')");
			stmt.execute("insert into SONG values('S0000005','3:45','I Want It All','C0000001')");
			stmt.execute("insert into SONG values('S0000006','5:01','No. 1 Party Anthem','C0000001')");
			stmt.execute("insert into SONG values('S0000007','3:50','Mad Sounds','C0000001')");
			stmt.execute("insert into SONG values('S0000008','4:33','Fireside','C0000001')");
			stmt.execute("insert into SONG values('S0000009','4:13','Why''d You Only Call Me When You''re High?','C0000001')");
			stmt.execute("insert into SONG values('S0000010','4:56','Snap Out Of It','C0000001')");
			stmt.execute("insert into SONG values('S0000011','3:56','Knee Socks','C0000001')");
			stmt.execute("insert into SONG values('S0000012','4:20','I Wanna Be Yours','C0000001')");
			stmt.execute("insert into SONG values('S0000013','3:12','Give Life Back to Music','C0000002')");
			stmt.execute("insert into SONG values('S0000014','4:12','The Game of Love','C0000002')");
			stmt.execute("insert into SONG values('S0000015','4:02','Giorgio by Moroder','C0000002')");
			stmt.execute("insert into SONG values('S0000016','3:58','Within','C0000002')");
			stmt.execute("insert into SONG values('S0000017','3:45','Instant Crush','C0000002')");
			stmt.execute("insert into SONG values('S0000018','5:01','Lose Yourself to Dance','C0000002')");
			stmt.execute("insert into SONG values('S0000019','3:50','Touch','C0000002')");
			stmt.execute("insert into SONG values('S0000020','4:33','Get Lucky','C0000002')");
			stmt.execute("insert into SONG values('S0000021','4:13','Beyond','C0000002')");
			stmt.execute("insert into SONG values('S0000022','4:56','Motherboard','C0000002')");
			stmt.execute("insert into SONG values('S0000023','3:56','Fragments of Time','C0000002')");
			stmt.execute("insert into SONG values('S0000024','4:20','Doin'' it Right','C0000002')");
			stmt.execute("insert into SONG values('S0000025','3:50','Contact','C0000002')");
			stmt.execute("insert into SONG values('S0000026','3:12','Love In The Future (Intro)','C0000003')");
			stmt.execute("insert into SONG values('S0000027','4:12','The Beginning...','C0000003')");
			stmt.execute("insert into SONG values('S0000028','4:02','Open Your Eyes','C0000003')");
			stmt.execute("insert into SONG values('S0000029','3:58','Made To Love','C0000003')");
			stmt.execute("insert into SONG values('S0000030','3:45','Who Do We Think We Are','C0000003')");
			stmt.execute("insert into SONG values('S0000031','5:01','All Of Me','C0000003')");
			stmt.execute("insert into SONG values('S0000032','3:50','Hold On Longer','C0000003')");
			stmt.execute("insert into SONG values('S0000033','4:33','Save The Night','C0000003')");
			stmt.execute("insert into SONG values('S0000034','4:13','Tomorrow','C0000003')");
			stmt.execute("insert into SONG values('S0000035','4:56','What If I Told You? (Interlude)','C0000003')");
			stmt.execute("insert into SONG values('S0000036','3:56','Dreams','C0000003')");
			stmt.execute("insert into SONG values('S0000037','4:20','Wanna Be Loved','C0000003')");
			stmt.execute("insert into SONG values('S0000038','3:50','Angel (Interlude)','C0000003')");
			stmt.execute("insert into SONG values('S0000039','5:01','You and I (Nobody In The World)','C0000003')");
			stmt.execute("insert into SONG values('S0000040','3:50','Asylum','C0000003')");
			stmt.execute("insert into SONG values('S0000041','4:33','Caught Up','C0000003')");
			stmt.execute("insert into SONG values('S0000042','3:12','Tennis Court','C0000004')");
			stmt.execute("insert into SONG values('S0000043','4:12','400 Lux','C0000004')");
			stmt.execute("insert into SONG values('S0000044','4:02','Royals','C0000004')");
			stmt.execute("insert into SONG values('S0000045','3:58','Ribs','C0000004')");
			stmt.execute("insert into SONG values('S0000046','3:45','Buzzcut Season','C0000004')");
			stmt.execute("insert into SONG values('S0000047','5:01','Team','C0000004')");
			stmt.execute("insert into SONG values('S0000048','3:50','Glory And Gore','C0000004')");
			stmt.execute("insert into SONG values('S0000049','4:33','Still Sane','C0000004')");
			stmt.execute("insert into SONG values('S0000050','4:13','White Teeth Teens','C0000004')");
			stmt.execute("insert into SONG values('S0000051','4:56','A World Alone','C0000004')");
			stmt.execute("insert into SONG values('S0000052','3:12','You''re Gonna Need Someone On Your Side','C0000005')");
			stmt.execute("insert into SONG values('S0000053','4:12','Glamorous Glue','C0000005')");
			stmt.execute("insert into SONG values('S0000054','4:02','We''ll Let You Know','C0000005')");
			stmt.execute("insert into SONG values('S0000055','3:58','The National Front Disco','C0000005')");
			stmt.execute("insert into SONG values('S0000056','3:45','Certain People I Know','C0000005')");
			stmt.execute("insert into SONG values('S0000057','5:01','We Hate It When Our Friends Become Successful','C0000005')");
			stmt.execute("insert into SONG values('S0000058','3:50','You''re The One For Me, Fatty','C0000005')");
			stmt.execute("insert into SONG values('S0000059','4:33','Seasick, Yet Still Docked','C0000005')");
			stmt.execute("insert into SONG values('S0000060','4:13','I Know It''s Gonna Happen Someday','C0000005')");
			stmt.execute("insert into SONG values('S0000061','4:56','Tomorrow (US Mix)','C0000005')");
			stmt.execute("insert into SONG values('S0000062','3:12','Counting Stars','C0000006')");
			stmt.execute("insert into SONG values('S0000063','4:12','If I Lose Myself','C0000006')");
			stmt.execute("insert into SONG values('S0000064','4:02','Feel Again','C0000006')");
			stmt.execute("insert into SONG values('S0000065','3:58','What You Wanted','C0000006')");
			stmt.execute("insert into SONG values('S0000066','3:45','I Lived','C0000006')");
			stmt.execute("insert into SONG values('S0000067','5:01','Light It Up','C0000006')");
			stmt.execute("insert into SONG values('S0000068','3:50','Can''t Stop','C0000006')");
			stmt.execute("insert into SONG values('S0000069','4:33','Au Revoir','C0000006')");
			stmt.execute("insert into SONG values('S0000070','4:13','Burning Bridges','C0000006')");
			stmt.execute("insert into SONG values('S0000071','4:56','Something I Need','C0000006')");
			stmt.execute("insert into SONG values('S0000072','3:45','Preacher','C0000006')");
			stmt.execute("insert into SONG values('S0000073','5:01','Don''t Look Down','C0000006')");
			stmt.execute("insert into SONG values('S0000074','3:12','Come Together','C0000007')");
			stmt.execute("insert into SONG values('S0000075','4:12','Something','C0000007')");
			stmt.execute("insert into SONG values('S0000076','4:02','Maxwell''s Silver Hammer','C0000007')");
			stmt.execute("insert into SONG values('S0000077','3:58','Oh! Darling','C0000007')");
			stmt.execute("insert into SONG values('S0000078','3:45','Octopus''s Garden','C0000007')");
			stmt.execute("insert into SONG values('S0000079','5:01','I Want You (She''s So Heavy)','C0000007')");
			stmt.execute("insert into SONG values('S0000080','3:50','Here Comes The Sun','C0000007')");
			stmt.execute("insert into SONG values('S0000081','4:33','Because','C0000007')");
			stmt.execute("insert into SONG values('S0000082','4:13','You Never Give Me Your Money','C0000007')");
			stmt.execute("insert into SONG values('S0000083','4:56','Sun King','C0000007')");
			stmt.execute("insert into SONG values('S0000084','3:45','Mean Mr. Mustard','C0000007')");
			stmt.execute("insert into SONG values('S0000085','5:01','Polythene Pam','C0000007')");
			stmt.execute("insert into SONG values('S0000086','3:58','She Came In Through The Bathroom Window','C0000007')");
			stmt.execute("insert into SONG values('S0000087','3:45','Golden Slumbers','C0000007')");
			stmt.execute("insert into SONG values('S0000088','5:01','Carry That Weight','C0000007')");
			stmt.execute("insert into SONG values('S0000089','3:50','The End','C0000007')");
			stmt.execute("insert into SONG values('S0000090','4:33','Her Majesty','C0000007')");
			stmt.execute("insert into SONG values('S0000091','3:12','Stinkfist','C0000008')");
			stmt.execute("insert into SONG values('S0000092','4:12','Eulogy','C0000008')");
			stmt.execute("insert into SONG values('S0000093','4:02','H.','C0000008')");
			stmt.execute("insert into SONG values('S0000094','3:58','Useful Idiot','C0000008')");
			stmt.execute("insert into SONG values('S0000095','3:45','Forty Six And 2','C0000008')");
			stmt.execute("insert into SONG values('S0000096','5:01','Message To Harry Manback','C0000008')");
			stmt.execute("insert into SONG values('S0000097','3:50','Hooker With A Penis','C0000008')");
			stmt.execute("insert into SONG values('S0000098','4:33','Intermission','C0000008')");
			stmt.execute("insert into SONG values('S0000099','4:13','Jimmy','C0000008')");
			stmt.execute("insert into SONG values('S0000100','4:56','Die Eier Von Satan','C0000008')");
			stmt.execute("insert into SONG values('S0000101','3:45','Pushit','C0000008')");
			stmt.execute("insert into SONG values('S0000102','5:01','Cesaro Summability','C0000008')");
			stmt.execute("insert into SONG values('S0000103','3:58','Aenema','C0000008')");
			stmt.execute("insert into SONG values('S0000104','3:45','(-) Ions','C0000008')");
			stmt.execute("insert into SONG values('S0000105','5:01','Third Eye','C0000008')");
			
			
			stmt.execute("insert into DVD values('D0000001',135,'Captain Phillips','Columbia Pictures',12.58,26.99,'D0000009')");
			stmt.execute("insert into DVD values('D0000002',118,'Dallas Buyers Club','Focus Features',13.50,24.99,'D0000010')");
			stmt.execute("insert into DVD values('D0000003',91,'Gravity','Warner Brothers',12.55,24.99,'D0000011')");
			stmt.execute("insert into DVD values('D0000004',146,'The Hunger Games - Catching Fire','Lionsgate',13.60,25.99,'D0000012')");
			stmt.execute("insert into DVD values('D0000005',113,'Thor - The Dark World','Walt Disney',11.59,21.99,'D0000013')");
			
			
			stmt.execute("insert into GAME values('G0000001','UBI SOFT','Sony Playstation 4','Assassin''s Creed IV Black Flag',26.40,45.99,'D0000014')");
			stmt.execute("insert into GAME values('G0000002','Activision','Microsoft Xbox 360','Call of Duty - Ghosts',18.20,26.99,'D0000015')");
			stmt.execute("insert into GAME values('G0000003','Rockstar Games','Microsoft Xbox 360','Grand Theft Auto V',26.50,44.99,'D0000016')");
			stmt.execute("insert into GAME values('G0000004','Konami','Sony Playstation 4','Metal Gear Solid V Ground Zeroes',17.60,24.99,'D0000017')");
			stmt.execute("insert into GAME values('G0000005','Capcom','Microsoft Xbox One','Dead Rising 3',26.50,44.99,'D0000018')");
			stmt.execute("insert into GAME values('G0000006','EA Games','Microsoft Xbox One','Titanfall',25.40,43.99,'D0000019')");
			stmt.execute("insert into GAME values('G0000007','Ubisoft','Nintendo Wii U','Rayman Legends',10.20,16.99,'D0000020')");
			stmt.execute("insert into GAME values('G0000008','Nintendo','Nintendo Wii U','Super Mario Bros.U',23.40,31.99,'D0000021')");
			
			
			stmt.execute("insert into CONSOLE values('L0000001',500,'Y',1,200,260,'E0000001')");
			stmt.execute("insert into CONSOLE values('L0000002',4,'Y',1,120,200,'E0000002')");
			stmt.execute("insert into CONSOLE values('L0000003',32,'Y',1,160,250,'E0000009')");
			stmt.execute("insert into CONSOLE values('L0000004',500,'Y',2,420,499.99,'E0000010')");
			
			
			stmt.execute("insert into HEADPHONES values('H0000001','Y','N','Y',242.69,360.95,'E0000003')");
			stmt.execute("insert into HEADPHONES values('H0000002','Y','N','Y',88.50,119.99,'E0000004')");
			stmt.execute("insert into HEADPHONES values('H0000003','N','N','Y',21.50,31.99,'E0000005')");
			stmt.execute("insert into HEADPHONES values('H0000004','N','Y','Y',2.50,6.99,'E0000006')");
			
			
			stmt.execute("insert into SOUND_DOCK values('SD0000001','N',60,'Y',142.50,199.99,'E0000007')");
			stmt.execute("insert into SOUND_DOCK values('SD0000002','Y',100,'Y',425.90,589.99,'E0000008')");
			// employee inserts
			stmt.execute("insert into EMPLOYEE values(1234, 'Horatio','McTiernan', 50,'Old Town','Maynooth','Kildare','4575885P',1234,'N')");
			stmt.execute("insert into EMPLOYEE values(5678, 'Steve','The Manager', 12,'New Town','Maynooth','Kildare','985845D',5678,'Y')");
			// transaction inserts
			stmt.execute("insert into transaction values(1000000, '01Jan2014', 'S', 224.98, 1, 1234,'P0000024')");
			stmt.execute("insert into transaction values(1000000, '01Jan2014', 'S', 224.98, 1, 1234,'P0000003')");
			stmt.execute("insert into transaction values(1000001, '30Aug2013', 'S', 302.98, 1, 1234,'P0000018')");
			stmt.execute("insert into transaction values(1000001, '30Aug2013', 'S', 302.98, 1, 1234,'P0000010')");
			stmt.execute("insert into transaction values(1000001, '30Aug2013', 'S', 302.98, 1, 1234,'P0000003')");
			stmt.execute("insert into transaction values(1000002, '25Apr2013', 'S', 770.95, 2, 5678,'P0000001')");
			stmt.execute("insert into transaction values(1000002, '25Apr2013', 'S', 770.95, 1, 5678,'P0000009')");
			stmt.execute("insert into transaction values(1000002, '25Apr2013', 'S', 770.95, 1, 5678,'P0000021')");
			stmt.execute("insert into transaction values(1000002, '25Apr2013', 'S', 770.95, 1, 5678,'P0000025')");
			stmt.execute("insert into transaction values(1000003, '16Mar2013', 'R', 16.99, 1, 5678,'P0000001')");
			stmt.execute("insert into transaction values(1000004, '15Dec2013', 'S', 1319.89, 1, 1234,'P0000004')");
			stmt.execute("insert into transaction values(1000004, '15Dec2013', 'S', 1319.89, 1, 1234,'P0000018')");
			stmt.execute("insert into transaction values(1000004, '15Dec2013', 'S', 1319.89, 1, 1234,'P0000017')");
			stmt.execute("insert into transaction values(1000004, '15Dec2013', 'S', 1319.89, 2, 1234,'P0000006')");
			stmt.execute("insert into transaction values(1000004, '15Dec2013', 'S', 1319.89, 1, 1234,'P0000020')"); //360.95
			stmt.execute("insert into transaction values(1000004, '15Dec2013', 'S', 1319.89, 1, 1234,'P0000022')"); //31.99
			stmt.execute("insert into transaction values(1000004, '15Dec2013', 'S', 1319.89, 1, 1234,'P0000025')");
			stmt.execute("insert into transaction values(1000005, '28Jun2013', 'S', 69.97, 1, 1234,'P0000011')"); //24.99
			stmt.execute("insert into transaction values(1000005, '28Jun2013', 'S', 69.97, 1, 1234,'P0000015')"); //26.99
			stmt.execute("insert into transaction values(1000005, '28Jun2013', 'S', 69.97, 1, 1234,'P0000003')"); //17.99
			stmt.execute("insert into transaction values(1000007, '02Jul2013', 'S', 24.99, 1, 5678,'P0000017')"); //24.99
			stmt.execute("insert into transaction values(1000008, '12Oct2013', 'R', 589.99, 1, 5678,'P0000025')"); //589.99
			stmt.execute("insert into transaction values(1000009, '11Sep2013', 'S', 199.99, 1, 1234,'P0000024')"); //199.99
			stmt.execute("insert into transaction values(1000010, '09Nov2013', 'R', 19.99, 1, 5678,'P0000005')"); //19.99
			stmt.execute("insert into transaction values(1000011, '16Feb2013', 'S', 53.97, 3, 1234,'P0000003')"); //53.97
			stmt.execute("insert into transaction values(1000012, '28Apr2013', 'S', 44.99, 1, 1234,'P0000016')"); //44.99
			stmt.execute("insert into transaction values(1000013, '15Dec2013', 'S', 18.99, 1, 1234,'P0000002')"); //18.99
			stmt.execute("insert into transaction values(1000014, '11Mar2013', 'S', 249.97, 2, 1234,'P0000017')"); //49.98
			stmt.execute("insert into transaction values(1000014, '11Mar2013', 'S', 249.97, 1, 1234,'P0000024')"); //199.99
			stmt.execute("insert into transaction values(1000015, '26Nov2013', 'S', 637.88, 1, 1234,'P0000001')"); //16.99
			stmt.execute("insert into transaction values(1000015, '26Nov2013', 'S', 637.88, 1, 1234,'P0000004')"); //19.99
			stmt.execute("insert into transaction values(1000015, '26Nov2013', 'S', 637.88, 1, 1234,'P0000009')"); //26.99
			stmt.execute("insert into transaction values(1000015, '26Nov2013', 'S', 637.88, 1, 1234,'P0000011')"); //24.99
			stmt.execute("insert into transaction values(1000015, '26Nov2013', 'S', 637.88, 1, 1234,'P0000021')"); //119.99
			stmt.execute("insert into transaction values(1000015, '26Nov2013', 'S', 637.88, 1, 1234,'P0000014')"); //45.99
			stmt.execute("insert into transaction values(1000015, '26Nov2013', 'S', 637.88, 1, 1234,'P0000013')"); //21.99
			stmt.execute("insert into transaction values(1000015, '26Nov2013', 'S', 637.88, 1, 1234,'P0000020')"); //360.95
			stmt.execute("insert into transaction values(1000016, '30May2013', 'S', 18.99, 1, 1234,'P0000012')"); //25.99
			stmt.execute("insert into transaction values(1000016, '30May2013', 'S', 18.99, 1, 1234,'P0000015')"); //26.99
			stmt.execute("insert into transaction values(1000016, '30May2013', 'S', 18.99, 2, 1234,'P0000007')"); //23.98
			stmt.execute("insert into transaction values(1000016, '30May2013', 'S', 18.99, 1, 1234,'P0000008')"); //16.99
			stmt.execute("insert into transaction values(1000016, '30May2013', 'S', 18.99, 1, 1234,'P0000004')"); //19.99
			
			ta.append("Data inserted successfully.");
		} catch (SQLException ex)
		{
			ta.append("ERROR: " + ex.getMessage());
		}
	}

	public void closeDB()
	{
		try
		{
			stmt.close();
			conn.close();
			ta.append("\nConnection closed");
		} catch (SQLException e)
		{
			ta.append("\nCould not close connection ");
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		new DBsql();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(jcb.getSelectedItem().toString().equals("Tallaght Database"))
		{
			url.setText(tallaghtDB);
		}
		else if(jcb.getSelectedItem().toString().equals("Local Database"))
		{
			url.setText(localDB);
		}
		
		if(e.getSource()==connect)
		{
			try {
				OracleDataSource ods = new OracleDataSource();

				passw = new String(pswd.getPassword());
				ods.setURL(url.getText());
				ods.setUser(username.getText());
				ods.setPassword(passw);
				conn = ods.getConnection();
				ta.append("Connection established.\n");
				
				dropTables();
				createSequence();
				createTables();
				inserts();
				closeDB();
			} catch (Exception ex) {
				ta.append("Unable to load driver ");
			}
		}
	}
}