package ull.herramientas.opendatachartgenerator.salida;

import java.text.DecimalFormat;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import ull.herramientas.opendatachartgenerator.Dataset;
import ull.herramientas.opendatachartgenerator.Instancia;
/**
 * \class GenerarSalidaGraficoPastel
 * \brief clase encargarda de mostrar un gráfico de pastel
 * 
 * \author Orlandy Ariel Sánchez A.
 *
 */
public class GenerarSalidaGraficoPastel implements IGenerarSalida
{
	// ATRIBUTOS
	private DefaultPieDataset mDatasetChartPie;
	private JFreeChart mSalida;
	private Dataset mDataset;

	// CONSTRUCTOR/ES Y METODOS
	public GenerarSalidaGraficoPastel(Dataset aDataset)
	{
		mDataset = aDataset;
		configurarDataSet();
		salidaPDF();
	}

	/**
	 * \brief Método configurar el DataSet
	 */
	private void configurarDataSet()
	{
		mDatasetChartPie = new DefaultPieDataset();

		ArrayList<String> tBarrios = mDataset.getColumna(2);

		for (int i = 0; i < tBarrios.size(); i++)
		{
			ArrayList<Instancia> tArrInstancias = mDataset.getRows();
			for (int j = 0; j < tArrInstancias.size(); j++)
			{
				Instancia tInstancia = tArrInstancias.get(i);
				Double tTotalTuristasPorBarrio = Double.parseDouble(tInstancia.getValorItem(26))
						+ Double.parseDouble(tInstancia.getValorItem(48));
				Double t_porcentajePorBarrio = tTotalTuristasPorBarrio / 100;
				mDatasetChartPie.setValue(tBarrios.get(i), t_porcentajePorBarrio);
			}
		}
	}

	@Override
	public void salidaGrafica()
	{
		// Mostrar Grafico
		ChartFrame tGraficoChartFrame = new ChartFrame("Pastel", mSalida);
		tGraficoChartFrame.setVisible(true);
		tGraficoChartFrame.setSize(1300, 1000);
	}

	@Override
	public JFreeChart salidaPDF()
	{
		mSalida = ChartFactory.createPieChart("Gráfico de Pastel", mDatasetChartPie, true, true, false);

		PiePlot tPlot = (PiePlot) mSalida.getPlot();/// añade el porcetaje a la  etiqueta
		tPlot.setSimpleLabels(true);
		PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
				"{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%")
		);/// Formatea el porcentaje
		tPlot.setLabelGenerator(gen);
		return mSalida;
	}

}
