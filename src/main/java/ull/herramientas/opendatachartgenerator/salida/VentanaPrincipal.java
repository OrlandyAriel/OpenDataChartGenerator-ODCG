package ull.herramientas.opendatachartgenerator.salida;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import ull.herramientas.opendatachartgenerator.Dataset;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

/**
 * Ventana principal de la aplicación
 * @author Mauricio
 *
 */
public class VentanaPrincipal {

	private JFrame frame;

	/**
	 * Lanza la aplicación.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea la aplicación.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Inicializa los contenidos del frame.
	 */
	private void initialize() {
		//Creación de ventana
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//Creación de radio botón de conjunto de datos de población de Barrios de Santa Cruz de Tenerife
		JRadioButton rdbtnCsv = new JRadioButton(".csv");
		rdbtnCsv.setBounds(127, 143, 67, 23);
		frame.getContentPane().add(rdbtnCsv);

		JRadioButton rdbtntxt = new JRadioButton(".txt");
		rdbtntxt.setBounds(196, 143, 67, 23);
		frame.getContentPane().add(rdbtntxt);

		rdbtnCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtntxt.isSelected())
				{
					rdbtntxt.setSelected(false);
				}
			}
		});

		rdbtntxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnCsv.isSelected())
				{
					rdbtnCsv.setSelected(false);
				}
			}
		});
        //Creación de título de aplicación
		JLabel lblOpenDataChart = new JLabel("Open Data Chart Generator");
		lblOpenDataChart.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblOpenDataChart.setBounds(67, 55, 357, 28);
		frame.getContentPane().add(lblOpenDataChart);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(77, 94, 302, 20);
		frame.getContentPane().add(comboBox);

		DefaultComboBoxModel datasets = new DefaultComboBoxModel();
		datasets.addElement("Población de barrios de Santa Cruz de Tenerife");
		comboBox.setModel(datasets);


        //Creación de botón para procesar el conjunto de datos seleccionado
		JButton btnProcesarConjuntoDe = new JButton("Procesar conjunto de datos");
		btnProcesarConjuntoDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dataset dataset;
				try {
					String urlDataset = "";
					if (comboBox.getSelectedIndex() == 0)
					{
						if (rdbtnCsv.isSelected())
						{
							urlDataset = "http://www.santacruzdetenerife.es/opendata/dataset/8363b662-0bdc-47e1-b9f6-65b536714f29/resource/ee814891-ba52-4e7c-b9e6-017c1bc43b6b/download/barrios.csv";
						}
						else if (rdbtntxt.isSelected())
						{
							urlDataset = "";

						}

					}
					//Se extrae y almacenan los datos del conjunto del url en un objeto de clase Dataset
					dataset = new Dataset(urlDataset);
					//Se crea la ventana de selección de gráficos de la información del conjunto de datos
					SalidaGraficosFrame seleccion_graficos = new SalidaGraficosFrame(dataset);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnProcesarConjuntoDe.setBounds(132, 184, 193, 23);
		frame.getContentPane().add(btnProcesarConjuntoDe);








	}
}
