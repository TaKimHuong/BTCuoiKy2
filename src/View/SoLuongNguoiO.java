package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import Controller.DBControler;

public class SoLuongNguoiO extends JPanel {
	Vector vData ;
	Vector vTitle ;
	ResultSet rs ;
	int collum = 0;
	ResultSetMetaData rss=null;
	private DefaultTableModel df;
	private JTable jtb;
	DBControler db;
	private JScrollPane tb_TK;
	private DefaultTableModel model_table;
	 private ExecutorService executor;
	 public void SoLuong() {
		db = new DBControler();
		// TODO Auto-generated method stub
		try {
			rs = db.stmt.executeQuery("SELECT quanlikitucxa.phongktx.MaPhong , count(quanlikitucxa.sinhvienktx.MaSV)'SoLuongHienTai' FROM quanlikitucxa.phongktx inner join quanlikitucxa.sinhvienktx on quanlikitucxa.phongktx.MaPhong "
					+ " = quanlikitucxa.sinhvienktx.MaPhong group by quanlikitucxa.phongktx.MaPhong");
			rss = (ResultSetMetaData) rs.getMetaData();
			collum = rss.getColumnCount();
			vTitle = new Vector(collum);
			for (int i = 1; i <= collum; i++) {
				vTitle.add(rss.getColumnLabel(i));
				System.out.println(rss.getColumnLabel(i));
			}
			vData = new Vector(10, 10);
			while (rs.next()) {
				Vector row = new Vector(collum);

				for (int i = 1; i <= collum; i++) {
					row.add(rs.getString(i));
					System.out.println(rs.getString(i));
				}
				vData.add(row);
			}

			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		df = new DefaultTableModel(vData, vTitle);
		jtb = new JTable(df);
		tb_TK = new JScrollPane(jtb, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
   				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		
	}
	public void Start() {
        new Thread(()->{
         SoLuong();
         executor = Executors.newFixedThreadPool(2);
         setSize(200,200);
         setLayout(new BorderLayout());
   		 tb_TK = new JScrollPane(jtb, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
   				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
   		add(tb_TK, BorderLayout.CENTER);
   		JButton bt = new JButton("Tìm kiếm");
   		add(bt, BorderLayout.SOUTH);
   		 bt.addActionListener(new ActionListener() {
   			
			@Override
			public void actionPerformed(ActionEvent e) {
				executor.execute(new Runnable() {
					

					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("okokok");
						model_table = (DefaultTableModel) jtb.getModel();
						int i_row = jtb.getSelectedRow();
						String MaPhong = model_table.getValueAt(i_row, 0)+"";
						System.out.println(MaPhong);
						
						JFrame j = new JFrame();
						j.setTitle("Kết quả");
						j.setSize(500, 350);
						j.setLocationRelativeTo(null);
						j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						java.net.URL url_icon_notpad = DsSinhVien.class.getResource("person-icon.png");
						Image img = Toolkit.getDefaultToolkit().createImage(url_icon_notpad);
						j.setIconImage(img);
						j.setLayout(new BorderLayout());
						// TODO Auto-generated method stub

						try {
							rs = db.stmt.executeQuery(
									"SELECT * FROM quanlikitucxa.sinhvienktx where  "
											+ "MaPhong = '"+MaPhong+"'");
							rss = (ResultSetMetaData) rs.getMetaData();
							collum = rss.getColumnCount();
							vTitle = new Vector(collum);
							for (int i = 1; i <= collum; i++) {
								vTitle.add(rss.getColumnLabel(i));
							}
							vData = new Vector(10, 10);
							while (rs.next()) {
								Vector row = new Vector(collum);

								for (int i = 1; i <= collum; i++) {
									row.add(rs.getString(i));
								}
								vData.add(row);
							}

							rs.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						df = new DefaultTableModel(vData, vTitle);
						jtb = new JTable(df);

						tb_TK = new JScrollPane(jtb, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
								JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						j.add(tb_TK, BorderLayout.CENTER);
						JPanel pn_South = new JPanel();
						pn_South.setLayout(new FlowLayout());
						JButton bt_QuayLai = new JButton("Quay lại");
						pn_South.add(bt_QuayLai);
						JButton bt_Sua = new JButton("Xóa");
						pn_South.add(bt_Sua);
						bt_Sua.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								System.out.println("click");
								XoaSinhVien();
							}

							private void XoaSinhVien() {
								// TODO Auto-generated method stub
								try {
									int i_row = jtb.getSelectedRow();

									Vector st = (Vector) vData.elementAt(i_row);
									// tạo câu lệnh SQl xóa dữ liệu ra khỏi Bảng
									String sql = "Delete from sinhvienktx where MaSV = '" + st.elementAt(0) + "'";
									db.stmt.executeUpdate(sql);
									// xóa nội dùng của hàng tương ứng trong bảng
									vData.remove(i_row);

									// cập nhật lại
						

									df.fireTableDataChanged();

								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
						j.add(pn_South, BorderLayout.SOUTH);
						bt_QuayLai.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								new PhongView();
							}
						});

						j.setVisible(true);
						while(true) {
							System.out.println("ok");
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
				// TODO Auto-generated method stub
				

			//	});
			}
			
		});
        }).start();

    }


}
