package Minesweeper;

import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class Minesweeper extends JFrame implements ActionListener {
	JButton[][] b;
	boolean[][] bombs;
	boolean firstTimeClick = true;
	
	
	private void init(int num) {
		Random r = new Random();
		int count = 0;
		while (count < num) {
			int i = r.nextInt(8);
			int j = r.nextInt(8);
			if (!bombs[i][j]) {
				bombs[i][j] = true;
				b[i][j].setText("");
				count++;
			}
		}
	}

	public Minesweeper(String name) {
		super(name);

		b = new JButton[9][9];
		bombs = new boolean[9][9];

		JPanel frame = new JPanel();
		
		frame.setLayout(new GridLayout(9, 9));
		setSize(500, 500);
		b = new JButton[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				bombs[i][j] = false;
				b[i][j] = new JButton();
				b[i][j].addActionListener(this);
				frame.add(b[i][j]);
			}
		}
		add(frame);
		init(12);
	}

	public void actionPerformed(ActionEvent e) {
		JButton a = (JButton) e.getSource();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (a == b[i][j]) {
					System.out.println(i + " " + j);

					openMap(i, j);
					bombClicked(i, j);

				}
			}
		}
	}

	public void bombClicked(int i, int j) {
		if (bombs[i][j]) {
			JOptionPane.showMessageDialog(null, "noob");
			for (int k = 0; k < 9; k++) {
				for (int l = 0; l < 9; l++) {
					if (bombs[k][l]) {
						b[k][l].setText("*");
					}
				}
			}
		}
	}

	public int checkMined(int i, int j) {
		int numMines = 0;
		for (int row = i - 1; row <= i + 1; row++) {
			for (int col = j - 1; col <= j + 1; col++) {

				if (row >= 0 && row < 9 && col >= 0 && col < 9) {
					if (bombs[row][col])
						numMines++;
				}
			}
		}
		return numMines;
	}

	public void openMap(int i, int j) {
		if (!b[i][j].isEnabled() || bombs[i][j]) {
			return;
		}

		b[i][j].setEnabled(false);
		b[i][j].setBackground(Color.black);
		if (checkMined(i, j) != 0)
			b[i][j].setText(checkMined(i, j) + "");

		if(checkMined(i, j)==0){
			
				if (i > 0)
					openMap(i - 1, j);
				if (j > 0)
					openMap(i, j - 1);
				if (i < 8)
					openMap(i + 1, j);
				if (j < 8)
					openMap(i, j + 1);
				if ((i > 0)&&(j >0))
					openMap(i - 1, j-1);
				if ((j > 0)&&(i<8))
					openMap(i + 1, j - 1);
				if ((i < 8)&&(j<8))
					openMap(i + 1, j+1);
				if ((j < 8)&&(i>0))
					openMap(i-1, j + 1);
			
		}
		
	}

	public static void main(String[] arsg) {
		Minesweeper a = new Minesweeper("lol");
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a.setVisible(true);
	}

}
