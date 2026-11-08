import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.YearMonth;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CalendarApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<Integer> yearComboBox;
	private JComboBox<String> monthComboBox_1;
	private JPanel panel_1;
	
	// أسماء أيام الأسبوع باللغة الإنجليزية
	private final String[] weekDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
	private JButton btnNewButton;

	/**
	 * تشغيل التطبيق
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalendarApp frame = new CalendarApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * إنشاء نافذة التطبيق والتحكم بالعناصر
	 */
	public CalendarApp() {
		setTitle("Calendar Year");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		btnNewButton = new JButton("New button");
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		
		// لوحة التحكم في الأعلى لاختيار السنة والشهر
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		JLabel lblNewLabel = new JLabel("Year: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblNewLabel);
		
		yearComboBox = new JComboBox<>();
		int currentYear = LocalDate.now().getYear();
		// إضافة نطاق من السنوات حول السنة الحالية
		for (int y = currentYear - 50; y <= currentYear + 50; y++) {
			yearComboBox.addItem(y);
		}
		yearComboBox.setSelectedItem(currentYear);
		panel.add(yearComboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Month: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblNewLabel_1);
		
		// أسماء الشهور باللغة الإنجليزية
		String[] months = {
			"January", "February", "March", "April", "May", "June", 
			"July", "August", "September", "October", "November", "December"
		};
		monthComboBox_1 = new JComboBox<>(months);
		monthComboBox_1.setSelectedIndex(LocalDate.now().getMonthValue() - 1);
		panel.add(monthComboBox_1);
		
		// لوحة شبكة التقويم في المنتصف
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 7, 5, 5));

		// ربط الأحداث لتحديث التقويم تلقائياً عند تغيير الاختيار
		yearComboBox.addActionListener(e -> updateCalendar());
		monthComboBox_1.addActionListener(e -> updateCalendar());

		// تحميل التقويم عند التشغيل لأول مرة
		updateCalendar();
	}

	/**
	 * دالة تحديث ورسم التقويم ديناميكياً
	 */
	private void updateCalendar() {
		panel_1.removeAll();

		// 1. إضافة عناوين أيام الأسبوع
		for (String day : weekDays) {
			JLabel lblHeader = new JLabel(day, SwingConstants.CENTER);
			lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblHeader.setForeground(new Color(0, 102, 204));
			panel_1.add(lblHeader);
		}

		// 2. جلب السنة والشهر المختارين
		int year = (int) yearComboBox.getSelectedItem();
		int monthIndex = monthComboBox_1.getSelectedIndex() + 1;
		
		YearMonth yearMonth = YearMonth.of(year, monthIndex);
		LocalDate firstOfMonth = yearMonth.atDay(1);
		
		int dayOfWeekValue = firstOfMonth.getDayOfWeek().getValue();
		int startDayOffset = (dayOfWeekValue % 7); // ضبط بداية الأسبوع
		int daysInMonth = yearMonth.lengthOfMonth();

		// 3. إضافة مساحات فارغة للأيام التي تسبق بداية الشهر
		for (int i = 0; i < startDayOffset; i++) {
			panel_1.add(new JLabel(""));
		}

		// 4. جلب التاريخ الحالي لتمييزه
		int todayDay = LocalDate.now().getDayOfMonth();
		int todayMonth = LocalDate.now().getMonthValue();
		int todayYear = LocalDate.now().getYear();

		// 5. إضافة أزرار أيام الشهر
		for (int day = 1; day <= daysInMonth; day++) {
			JButton dayButton = new JButton(String.valueOf(day));
			dayButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
			dayButton.setFocusPainted(false);
			
			// تمييز اليوم الحالي بلون مميز
			if (day == todayDay && monthIndex == todayMonth && year == todayYear) {
				dayButton.setBackground(new Color(173, 216, 230));
				dayButton.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			
			panel_1.add(dayButton);
		}

		panel_1.revalidate();
		panel_1.repaint();
	}
}