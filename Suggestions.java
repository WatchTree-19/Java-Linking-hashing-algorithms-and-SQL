import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Suggestions extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Suggestions frame = new Suggestions();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Suggestions() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground( Color.decode("#ffffff"));
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("FitBud Lifestyle Education");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(284, 11, 195, 59);
		contentPane.add(lblNewLabel_1);
		
		JTextPane txtpnifItsNot = new JTextPane();
		txtpnifItsNot.setText("\"We are what we repeatedly do\" ");
		txtpnifItsNot.setBounds(0, 56, 733, 44);
		contentPane.add(txtpnifItsNot);
		
		JLabel lblNewLabel = new JLabel("");
		Image typicalLifestylelunch = new ImageIcon(this.getClass().getResource("Typical lifestyle.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(typicalLifestylelunch));
		lblNewLabel.setBounds(0, 96, 427, 230);
		contentPane.add(lblNewLabel);
		
		JTextPane txtpnIfThisIs = new JTextPane();
		txtpnIfThisIs.setText("If this is your typical lifestyle amounting to 2070 calories with heavy saturated fats, then your conquest for your desired physique is over before it has begun. In order to build muscle and burn fat, your body must be made to use these fats in pressured circumstances or have no other energy source, as a result of this whether your goal is to lose fat or bulk this is definitely not the lunch you want to consume. \r\nTo start with you must determine your goal, is it to lose weight, build muscle or gain weight?\r\nIt is then important to be aware of how much you are currently consuming, this will then help you to adjust your eating habits to effectively utilise FitBud to achieve your goals. \r\nLets take an example: \r\nA 90kg man wants to lose weight and build muscle. \r\nFitBud would recommend this workout routine:\r\nSwimming 30 minutes x2 Monday and Thursday\r\nGym Shoulders and triceps Tuesday\r\nGym Chest and back Wednesday \r\nGym Legs and biceps Friday \r\nWhy? This routine exercises both anaerobic and aerobic methods of weight loss and muscle building which is vital when building endurance rather than just purely weight lifting.\r\nIn terms of his diet we would recommend this:\r\nBreakfast - 2 eggs, 2 pieces of ham , spinach and water. Why? Eggs are a brilliant source of protein simple and fast to make, however do mix it up as the cholesterol levels are considerably high for everyday intake, ham is low in fat and a good source of protein, a high protein diet is essential for muscle repair due to biological processes such as protein synthesis, spinach is overall an excellent for nourishment and vitamin supplementation. \r\nLunch - Chicken or prawn salad, almonds, yoghurt , banana. Why? Because prawns are simply so good in every department , protein, iron and vitamins are abundant , the salad is light but filling and will compliment your diet with nourishment, almonds are a good source of good fats instead of saturated fats which are harder to break down, a low fat muller yoghurt will suffice cravings as a little treat while staying controller, the abundant source of K+ ions from bananas are vital in nerve impulses and nervous coordination as they help to establish an action potential which aids muscle contraction.\r\nDinner - Lentil and chick pea soup with bread (or equivalent substitue), water or squash , weight watchers desert. Lentils are a valuable source of protein for starters , and also contain many vitamins important for regular body functions such as efficient kidney operation. The bread substitute can be experimented with, but generally when exercising a good source of carbohydrates is essential for energy supply. \r\nTotal calories: 2200KJ , 120g protein , carbohydrate values differ\r\nAlternatives and must haves for changing your lifestyle:\r\nUsing this program and sticking to the above diet is satisfactory, as we delve further into fitness, we come to understand that protein supplements are essential, with a typical shake containing 30g of protein , one of these before exercise will crank up your body performance and muscle repair. \r\nEating the same food everyday obviously is never going to happen or work, so here are a few suggestions on tasty alternatives: \r\nSweet potato fries, masale fish , tomato soup and pasta, quakers oats, jumbalaya, salmon, spinach and kale curry, chicken curry (provided side dishes are kept controlled), brussel sporuts with peas , sweetcorn and bacon slices, carrots and cauliflower curry.\r\n\r\n");
		txtpnIfThisIs.setBounds(0, 318, 733, 684);
		contentPane.add(txtpnIfThisIs);
		
		JButton btnNewButton = new JButton("Back to main menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenu openMainMenu = new MainMenu();
				openMainMenu.NewScreen();
			}
		});
		btnNewButton.setBounds(601, 11, 171, 23);
		contentPane.add(btnNewButton);
		
		
		
		
	}
}
