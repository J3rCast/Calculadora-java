package modelo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Calculadora {
	private JLabel resultado;
	private int dato1;
	private int dato2;
	private int res;
	private static boolean operador;
	private static char op;
	
	public static boolean isOperador() {
		return Calculadora.operador;
	}
	
	public static void setOperador(boolean o) {
		Calculadora.operador = o;
	}
	
	public static void setOp(char c) {
		Calculadora.op = c;
	}
	
	public static char getOp() {
		return Calculadora.op;
	}
	
	public int getDato1() {
		return this.dato1;
	}
	
	public int getDato2() {
		return this.dato2;
	}
	
	public int getRes() {
		return this.res;
	}
	
	public void setRes(int r) {
		this.res = r;
	}
	
	public void setDato1(int d) {
		this.dato1 = d;
	}
	
	public void setDato2(int d) {
		this.dato2 = d;
	}
	
	public Calculadora() {
		JFrame ventana = new JFrame("Calculadora");
		ventana.setSize(300, 400);

		this.resultado = new JLabel(String.valueOf(this.dato1), SwingConstants.RIGHT);
		resultado.setBackground(Color.black);
		resultado.setSize(400, 150);
		resultado.setFont(new Font("Arial", Font.ITALIC, 45));
		resultado.setBorder(new EmptyBorder(10, 10, 10, 10));
		resultado.setForeground(new java.awt.Color(245, 234, 232));
		resultado.setOpaque(true);
		ventana.add(resultado, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridLayout(4, 4));
		panel.setSize(400, 350);
		panel.setBackground(Color.black);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		ventana.add(panel);
		
		String[] botones = {
			"7", "8", "9", "/",
			"4", "5", "6", "*",
			"1", "2", "3", "-",
			"CE", "0", "=", "+"
		};
		
		for (int i = 0; i < botones.length; i++) {
			JButton boton = new JButton(botones[i]);
			boton.setFont(new Font("Arial", Font.BOLD, 20));
			boton.setBackground(new java.awt.Color(116, 106, 104 ));
			boton.setForeground(new java.awt.Color(245, 234, 232));
			boton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					resultado.setFont(new Font("Arial", Font.ITALIC, 45));
					if (boton.getText() == "+") {
						igual();
						Calculadora.setOp('+');
						igual();
					} else if(boton.getText() == "-") {
						igual();
						Calculadora.setOp('-');
						igual();
					} else if(boton.getText() == "*") {
						igual();
						setDato2(1);
						Calculadora.setOp('*');
						igual();
					} else if(boton.getText() == "/") {
						igual();
						setDato2(1);
						Calculadora.setOp('/');
						igual();
					} else if (boton.getText() == "=") {
						igual();
						Calculadora.setOp('\0');
					} else if (boton.getText() == "CE") {
						setDato1(0);
						setDato2(0);
						setRes(0);
						actualizarPantalla(getRes());
					} else if (Integer.parseInt(boton.getText()) <= 9 && Integer.parseInt(boton.getText()) >= 0) {
						if (isOperador()) {
							setDato2(getDato2() * 10 + Integer.parseInt(boton.getText()));
							actualizarPantalla(getDato2());
						} else {
							setDato1(getDato1() * 10 + Integer.parseInt(boton.getText()));
							actualizarPantalla(getDato1());
						}
						
					}
				}
			});
			panel.add(boton);
		}

		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);
	}
	
	public void igual() {
		setOperador(true);
		if (Calculadora.getOp() == '+') {
			setRes(getDato1() + getDato2());
			actualizarPantalla(getRes());
			setDato1(getRes());
			setDato2(0);
		} else if (Calculadora.getOp() == '-') {
			setRes(getDato1() - getDato2());
			actualizarPantalla(getRes());
			setDato1(getRes());
			setDato2(0);
		} else if (Calculadora.getOp() == '*') {
			setRes(getDato1() * getDato2());
			actualizarPantalla(getRes());
			setDato1(getRes());
			setDato2(0);
		} else if (Calculadora.getOp() == '/') {
			if (getDato2() == 0) {
				resultado.setFont(new Font("Arial", Font.ITALIC, 23));
				actualizarPantalla("No se puede dividir por 0");
				setDato1(0);
				setDato2(0);
				Calculadora.setOp('\0');
				setOperador(false);
			} else {
				setRes(getDato1() / getDato2());
				actualizarPantalla(getRes());
				setDato1(getRes());
				setDato2(0);
			}
		} else if (Calculadora.getOp() == '\0') {
			actualizarPantalla(0);
		}
		
	}
	
	public void actualizarPantalla(int d) {
		this.resultado.setText(Integer.toString(d));
	}
	
	public void actualizarPantalla(String s) {
		this.resultado.setText(s);
	}
}