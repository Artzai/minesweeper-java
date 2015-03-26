import javax.swing.*;

public class Boton extends JButton {
	
	private boolean mina, clicked;
	private int numero;	
	
	//constructor sin parametros
	public Boton () {
		this.setSize(45, 45);
	}
	
	//GETTERS
	public boolean getMina () {
		return this.mina;
	}	
	
	public int getNumero () {
		return this.numero;
	}	
	
	public boolean getClicked() {
		return this.clicked;
	}
	
	//SETTERS
	public void setClicked(boolean clic) {
		if (clic == true) {
			this.clicked = true;
		}
		else {
			this.clicked = false;
		}
	}
	
	public void setMina (boolean mina) {
		this.mina = mina;
	}
	
	public void setImagen (ImageIcon imagen) {
		this.setIcon(imagen);
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
}