import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Buscaminas extends JFrame implements ActionListener, MouseListener {
	
	private Boton icono;
	private Boton botones[][];
	private int random1, random2, margenHoriz = 10, margenVert = 10, anchura = 0, altura = 0;
	
	//ICONOS
	private ImageIcon minaIcon = new ImageIcon ("src/mina.png");
	private ImageIcon azulIcon = new ImageIcon ("src/azul.jpg");
	private ImageIcon banderaIcon = new ImageIcon ("src/bandera.gif");
	private ImageIcon banderaIcon2 = new ImageIcon ("src/bandera.gif");
	private ImageIcon smiley = new ImageIcon("src/smiley.gif");
	private ImageIcon poker = new ImageIcon("src/pokerface.png");
	private ImageIcon facepalm = new ImageIcon("src/facepalm.gif");
	
	//private TimerDisplay timer;
	
	private int cantMinas = 0;
	
	//MENUS
	private JMenuBar barraMenu;
	private JMenu archivo, juego;
	private JMenuItem nuevo, x5, x10, x15, volver, salir;
	
	//constructor (alto, ancho, minas)
	public Buscaminas (int alto, int ancho, int minas) {
		this.anchura = ancho;
		this.altura = alto;		
		setLayout (null);
		setBounds(300, 100, 525, 630);
		setTitle ("Buscaminas 1.4 (ßeta)");	
		this.setIconImage(minaIcon.getImage());
		this.cantMinas = minas;
		this.crearTablero(ancho, alto);
		this.colocarMinas(minas);	
		this.calculaNumero(botones);
		
		/*
		timer = new TimerDisplay();
		timer.setBounds(50, 520, 50, 50);
		add(timer);
		timer.start();
		*/
		
		icono = new Boton();
		icono.setImagen(poker);
		icono.addActionListener(this);
		add(icono);
		icono.setLocation(230, 520);
		
		//añado menus
		barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		
		archivo = new JMenu ("Archivo");
		barraMenu.add(archivo);
		
		juego = new JMenu ("Juego");
		barraMenu.add(juego);
		
		volver = new JMenuItem ("Volver al juego");
		volver.addActionListener(this);
		archivo.add(volver);
		
		salir = new JMenuItem ("Salir del juego");
		salir.addActionListener(this);
		archivo.add(salir);
		
		nuevo = new JMenuItem ("Nuevo juego");
		nuevo.addActionListener(this);
		juego.add(nuevo);
		
		x5 = new JMenuItem ("5x5");
		x5.addActionListener(this);
		juego.add(x5);
		
		x10 = new JMenuItem ("10x10");
		x10.addActionListener(this);
		juego.add(x10);
		
		x15 = new JMenuItem ("15x15");
		x15.addActionListener(this);
		juego.add(x15);	
	}
	
	//metodo que crea un tablero de Y x Z botones
	public void crearTablero (int alto, int ancho) {
		botones = new Boton [alto][ancho];
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {				
				botones[i][j] = new Boton ();
				botones[i][j].addActionListener(this);				
				botones[i][j].addMouseListener(this);				
				botones[i][j].setLocation(margenHoriz, margenVert);
				add(botones[i][j]);
				margenHoriz += 50;
				if (j == ancho - 1) {
					margenHoriz = 10;					
				}	
			}
			margenVert += 50;
		}
		margenVert = 10;
		margenHoriz = 10;
	}
	
	//metodo que coloca X minas aleatoriamente
	public void colocarMinas(int cantidad) {		
		for (int i = 1; i <= cantidad; i++) {
			random1 = ((int)(Math.random()*botones.length+0));
			random2 = ((int)(Math.random()*botones.length+0));
			if (botones[random1][random2].getMina() == false) {
				botones[random1][random2].setMina(true);	
				botones[random1][random2].setNumero(9);
			}
			else {
				i--;
			}
		}	
	}	
	
	//metodo que una vez puestas las minas calcula el numero de cada casilla restante
	public void calculaNumero(Boton[][] botones) {
		int cont = 0;
		for (int x = 0; x < botones.length; x++) {
			for (int y = 0; y < botones.length; y++) {
				if (botones[x][y].getMina() == false) {
					cont = 0;				
					/*si no hay mina en la posicion, busco botones en las 8 posiciones que la rodean
					 *
					 * primero trato las posiciones de las 4 esquinas y los bordes del tablero*/
					if (x == 0) {
						if (y == 0) {
							
							//esquina superior izquierda
							if (botones[0][1].getMina() == true) {
								cont++;
							}
							if (botones[1][1].getMina() == true) {
								cont++;
							}
							if (botones[1][0].getMina() == true) {
								cont++;
							}									
						}
						else {
							if (y == botones.length - 1) {
								
								//superior derecha
								if (botones[0][botones.length - 2].getMina() == true) {
									cont++;
								}
								if (botones[1][botones.length - 2].getMina() == true) {
									cont++;
								}
								if (botones[1][botones.length - 1].getMina() == true) {
									cont++;
								}
							}
							else {
								
								//borde superior (sin esquinas)
								if (botones [x][y-1].getMina() == true) {
									cont++;
								}
								if (botones [x][y+1].getMina() == true) {
									cont++;
								}
								if (botones [x+1][y-1].getMina() == true) {
									cont++;
								}
								if (botones [x+1][y].getMina() == true) {
									cont++;
								}
								if (botones [x+1][y+1].getMina() == true) {
									cont++;
								}
							}
						}
					}
					if (x == botones.length - 1) {
						if (y == 0) {
							
							//esquina inferior izquierda
							if (botones[botones.length - 1][1].getMina() == true) {
								cont++;
							}
							if (botones[botones.length - 2][1].getMina() == true) {
								cont++;
							}
							if (botones[botones.length - 2][0].getMina() == true) {
								cont++;
							}
						}
						else {
							if (y == botones.length - 1) {
								
								//inferior derecha
								if (botones[botones.length - 1][botones.length - 2].getMina() == true) {
									cont++;
								}
								if (botones[botones.length - 2][botones.length - 2].getMina() == true) {
									cont++;
								}
								if (botones[botones.length - 2][botones.length - 1].getMina() == true) {
									cont++;
								}
							}
							else {
								
								//borde inferior (sin esquinas)
								if (botones [x][y-1].getMina() == true) {
									cont++;
								}
								if (botones [x][y+1].getMina() == true) {
									cont++;
								}
								if (botones [x-1][y-1].getMina() == true) {
									cont++;
								}
								if (botones [x-1][y].getMina() == true) {
									cont++;
								}
								if (botones [x-1][y+1].getMina() == true) {
									cont++;
								}
							}
						}
					}
					if (x != 0 && x != botones.length - 1) {							
						if (y == 0) {
							
							//borde izquierdo (sin esquinas)
							if (botones [x-1][y].getMina() == true) {
								cont++;
							}
							if (botones [x+1][y].getMina() == true) {
								cont++;
							}
							if (botones [x-1][y+1].getMina() == true) {
								cont++;
							}
							if (botones [x][y+1].getMina() == true) {
								cont++;
							}
							if (botones [x+1][y+1].getMina() == true) {
								cont++;
							}
						}
						else {
							if (y == botones.length - 1) {
								
								//borde derecho (sin esquinas)
								if (botones [x-1][y].getMina() == true) {
									cont++;
								}
								if (botones [x+1][y].getMina() == true) {
									cont++;
								}
								if (botones [x-1][y-1].getMina() == true) {
									cont++;
								}
								if (botones [x][y-1].getMina() == true) {
									cont++;
								}
								if (botones [x+1][y-1].getMina() == true) {
									cont++;
								}
							}
						}
					}
					
					//si picha un boton central, aplico el tratamiento general
					if (x != 0 && x != botones.length - 1 && y != 0 && y != botones.length - 1) {
						cont = 0;
						if (botones [x-1][y].getMina() == true) {
							cont++;
						}
						if (botones [x-1][y+1].getMina() == true) {
							cont++;
						}
						if (botones [x][y+1].getMina() == true) {
							cont++;
						}
						if (botones [x+1][y+1].getMina() == true) {
							cont++;
						}
						if (botones [x+1][y].getMina() == true) {
							cont++;
						}
						if (botones [x+1][y-1].getMina() == true) {
							cont++;
						}
						if (botones [x][y-1].getMina() == true) {
							cont++;
						}
						if (botones [x-1][y-1].getMina() == true) {
							cont++;
						}
					}
					
					//el atributo numero de ese boton es la cantidad de minas que tiene alrededor
					botones[x][y].setNumero(cont);	
				}
			}
		}			
	}
	
	//metodo para descubrir el contenido de la casilla
	public void descubrePanel(int y, int z) {	
		if (botones[y][z].getClicked() == false) {
			if (botones[y][z].getMina() == true) {
				botones[y][z].setIcon(minaIcon);
				botones[y][z].setClicked(true);
				
				//pongo el icono de mina y recorro el panel entero para cambiar todos los iconos de mina
				for (int i = 0; i < botones.length; i++) {
					for (int j = 0; j < botones.length; j++) {
						if (botones[i][j].getMina() == true) {
							botones[i][j].setImagen(minaIcon);								
						}
						botones[i][j].removeMouseListener(this);
					}
				}
				for (int i = 0; i < botones.length; i++) {
					for (int j = 0; j < botones.length; j++) {
						botones[i][j].removeActionListener(this);
					}
				}
				icono.setImagen(facepalm);
			}
			else {
				
				//si el valor numerico no es 0, aparece un numero
				if (botones[y][z].getNumero() != 0) {
					botones[y][z].setText(String.valueOf(botones[y][z].getNumero()));
					botones[y][z].setClicked(true);
					botones[y][z].setBackground(Color.CYAN);
				}
				else {
					
					//si es 0, se pinta de azul
					botones[y][z].setIcon(azulIcon);
					botones[y][z].setClicked(true);				
				}
			}
			botones[y][z].removeActionListener(this);
			botones[y][z].setClicked(true);
			
			//llamo al metodo que busca las casillas azules del tablero
			this.buscarAzules();
			int cont = 0;
			for (int f = 0; f < botones.length; f++) {
				for (int g = 0; g < botones.length; g++) {
					if (botones[f][g].getClicked() == true && botones[f][g].getMina() == false) {
						cont++;
					}
				}
			}
			if (cont == botones.length * botones.length - this.cantMinas) {
				for (int f = 0; f < botones.length; f++) {
					for (int g = 0; g < botones.length; g++) {
						botones[f][g].removeActionListener(this);
						if (botones[f][g].getIcon() == banderaIcon && botones[f][g].getMina() == false) {
							this.descubrePanel(f, g);
							botones[f][g].setBackground(Color.RED);
						}
						if (botones[f][g].getMina() == true) {
							botones[f][g].setImagen(banderaIcon2);
						}
					}
				}
				icono.setImagen(smiley);
			}
		}
	}	
	
	//metodo que busca casillas azules y muestra las 8 casillas de alrededor
	public void buscarAzules() {
		for (int i = 0; i < botones.length; i++) {
			for (int j = 0; j < botones.length; j++) {
				if (botones[i][j].getIcon() == azulIcon) {
					if (i != 0 && i != botones.length - 1 && j != 0 && j != botones.length - 1) {
						descubrePanel(i, j + 1);
						descubrePanel(i, j - 1);
						descubrePanel(i + 1, j - 1);
						descubrePanel(i+1, j);
						descubrePanel(i+1, j+1);
						descubrePanel(i-1, j-1);
						descubrePanel(i-1, j);
						descubrePanel(i-1, j+1);
					}
					
					//esquina superior izquierda
					if (i == 0) {
						if (j == 0) {
							descubrePanel(i, j+1);
							descubrePanel(i+1, j);
							descubrePanel(i+1, j+1);
						}
						else {
							
							//esquina superior derecha
							if (j == botones.length - 1) {
								descubrePanel(i, j-1);
								descubrePanel(i+1, j);
								descubrePanel(i+1, j-1);
							}
							
							//borde superior
							else {
								descubrePanel(i, j-1);
								descubrePanel(i+1, j-1);
								descubrePanel(i+1, j);
								descubrePanel(i+1, j+1);
								descubrePanel(i, j+1);
							}
						}
					}
					else {
						
						//esquina inferior izquierda
						if (i == botones.length - 1) {
							if (j == 0) {
								descubrePanel(i-1, j);
								descubrePanel(i-1, j+1);
								descubrePanel(i, j+1);
							}
							else {
								
								//esquina inferior derecha
								if (j == botones.length - 1) {
									descubrePanel(i-1, j);
									descubrePanel(i-1, j-1);
									descubrePanel(i, j-1);
								}
								
								//borde inferior
								else {
									descubrePanel(i, j-1);
									descubrePanel(i-1, j-1);
									descubrePanel(i-1, j);
									descubrePanel(i-1, j+1);
									descubrePanel(i, j+1);
								}
							}
						}
						else {
							
							//borde izquierdo
							if (j == 0) {
								descubrePanel(i-1, j);
								descubrePanel(i-1, j+1);
								descubrePanel(i, j+1);
								descubrePanel(i+1, j+1);
								descubrePanel(i+1, j);
							}
							if (j == botones.length - 1) {
								
								//borde derecho
								descubrePanel(i-1, j);
								descubrePanel(i-1, j-1);
								descubrePanel(i, j-1);
								descubrePanel(i+1, j-1);
								descubrePanel(i+1, j);
							}
						}
					}
				}
			}
		}
	}
	
	//mouse click
	public void actionPerformed (ActionEvent e) {
		
		//recorro array en busca del boton pulsado
		for (int i = 0; i < botones.length; i++) {
			for (int j = 0; j < botones.length; j++) {
				if (e.getSource() == botones[i][j]) {
					this.descubrePanel(i, j);					
				}					
			}
		}
		
		if (e.getSource() == salir) {
			System.exit(0);
		}
		
		if (e.getSource() == nuevo) {			
			for (int i = 0; i < botones.length; i++) {
				for (int j = 0; j < botones.length; j++) {					
					this.remove(botones[i][j]);
				}
			}
			this.repaint();
			this.crearTablero(altura, anchura);
			this.colocarMinas(cantMinas);
			this.calculaNumero(botones);
			icono.setImagen(poker);
		}
		
		if (e.getSource() == x5) {
			for (int i = 0; i < botones.length; i++) {
				for (int j = 0; j < botones.length; j++) {					
					this.remove(botones[i][j]);
				}
			}
			this.repaint();
			this.altura = 5;
			this.anchura = 5;
			this.cantMinas = 4;
			this.crearTablero(altura, anchura);
			this.colocarMinas(cantMinas);
			this.calculaNumero(botones);
			setBounds(300, 100, 270, 370);
			icono.setLocation(110, 265);
			icono.setImagen(poker);
		}
		
		if (e.getSource() == x10) {
			for (int i = 0; i < botones.length; i++) {
				for (int j = 0; j < botones.length; j++) {					
					this.remove(botones[i][j]);
				}
			}
			this.repaint();
			this.altura = 10;
			this.anchura = 10;
			this.cantMinas = 15;
			this.crearTablero(altura, anchura);
			this.colocarMinas(cantMinas);
			this.calculaNumero(botones);
			setBounds(300, 100, 525, 630);
			icono.setLocation(230, 520);
			icono.setImagen(poker);
		}
		
		if (e.getSource() == x15) {
			for (int i = 0; i < botones.length; i++) {
				for (int j = 0; j < botones.length; j++) {					
					this.remove(botones[i][j]);
				}
			}
			this.repaint();
			this.altura = 15;
			this.anchura = 15;
			this.cantMinas = 50;
			this.crearTablero(altura, anchura);
			this.colocarMinas(cantMinas);
			this.calculaNumero(botones);
			setBounds(300, 100, 775, 870);
			icono.setLocation(360, 765);
			icono.setImagen(poker);
		}
		
		if (e.getSource() == icono) {
			for (int i = 0; i < botones.length; i++) {
				for (int j = 0; j < botones.length; j++) {					
					this.remove(botones[i][j]);
				}
			}
			this.repaint();
			this.crearTablero(altura, anchura);
			this.colocarMinas(cantMinas);
			this.calculaNumero(botones);
			icono.setImagen(poker);
		}
	}	
	
	public static void main(String[] args) {
		Buscaminas b1 = new Buscaminas (10, 10, 15);
		b1.setVisible(true);
		b1.setResizable(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int y = 0; y < botones.length; y++) {
			for (int z = 0; z < botones.length; z++) {
				if (e.getSource() == botones[y][z]) {
					if (e.getButton() == 3) {
						if (botones[y][z].getIcon() != banderaIcon && botones[y][z].getClicked() == false) {
							if (botones[y][z].getIcon() != banderaIcon2) {
								botones[y][z].setImagen(banderaIcon);
								botones[y][z].setClicked(true);
								botones[y][z].removeActionListener(Buscaminas.this);
							}
						}
						else {
							if (botones[y][z].getIcon() == banderaIcon) {
								botones[y][z].setIcon(null);
								botones[y][z].addActionListener(Buscaminas.this);
								botones[y][z].setClicked(false);											
							}
						}					                   
	                } 
				}
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {	
	}

	@Override
	public void mousePressed(MouseEvent arg0) {	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {	
	}
}