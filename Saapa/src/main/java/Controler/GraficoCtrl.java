/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import DAO.AlunoDao;
import Model.Aluno;
import Model.Avaliacao;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 
 */
public class GraficoCtrl {

    DefaultCategoryDataset ds = new DefaultCategoryDataset();

// cria o gráfico
    public OutputStream geraImc(Aluno a) throws IOException {
        AvaliacaoCtrl ad = new AvaliacaoCtrl();
        int i = 1;
        File f = new File("C:\\Users\\Edilson Jr\\Downloads\\Saapa\\src\\main\\webapp\\images\\graficos\\imc\\" + a.getMatricula() + "Imc.png");
        if (f.exists()) {
            f.delete();
        }
        List<Double> map = ad.geraImc(a);
        for (Iterator iterator = map.iterator(); iterator.hasNext();) {
            Double next = (Double) iterator.next();
            ds.addValue(next, "imc", String.valueOf(i));
            i++;
        }

        JFreeChart grafico = ChartFactory.createLineChart("Meu Grafico", "Avaliações",
                "Valor", ds, PlotOrientation.VERTICAL, true, true, false);

        OutputStream arquivo = new FileOutputStream("C:\\Users\\Edilson Jr\\Downloads\\Saapa\\src\\main\\webapp\\images\\graficos\\imc\\" + a.getMatricula() + "Imc.png");
        ChartUtilities.writeChartAsPNG(arquivo, grafico, 550, 400);
        arquivo.close();

        return arquivo;

    }

    public void geraRcq(Aluno pesquisado) {
        AvaliacaoCtrl ac = new AvaliacaoCtrl();
        List<Double> list = ac.gerarRcq(pesquisado);
        int i = 1;
        File f = new File("C:\\Users\\Edilson Jr\\Downloads\\Saapa\\src\\main\\webapp\\images\\graficos\\rcq\\" + pesquisado.getMatricula() + "rcq.png");
        if (f.exists()) {
            f.delete();
        }
        for (Double avaliacao : list) {

            ds.addValue(avaliacao, "Rqc", String.valueOf(i));
            i++;
        }
        try {
            JFreeChart grafico = ChartFactory.createLineChart("Meu Grafico", "Avaliações",
                    "Valor", ds, PlotOrientation.VERTICAL, true, true, false);

            OutputStream arquivo;

            arquivo = new FileOutputStream("C:\\Users\\Edilson Jr\\Downloads\\Saapa\\src\\main\\webapp\\images\\graficos\\rcq\\" + pesquisado.getMatricula() + "rcq.png");
            ChartUtilities.writeChartAsPNG(arquivo, grafico, 550, 400);
            System.out.println("Controler.GraficoCtrl.geraRqc()" + arquivo.toString());
            arquivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GraficoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GraficoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void geraGordura(Aluno pesquisado) {
        AvaliacaoCtrl ac = new AvaliacaoCtrl();
        List<Double> list = ac.gerarGordura(pesquisado);
        int i = 1;
        File f = new File("C:\\Users\\Edilson Jr\\Downloads\\Saapa\\src\\main\\webapp\\images\\graficos\\gordura\\" + pesquisado.getMatricula() + "gordura.png");
        if (f.exists()) {
            f.delete();
        }
        for (Double avaliacao : list) {

            ds.addValue(avaliacao, "gordura", String.valueOf(i));
            i++;
        }
        try {
            JFreeChart grafico = ChartFactory.createLineChart("Meu Grafico", "% de gordura",
                    "Valor", ds, PlotOrientation.VERTICAL, true, true, false);

            OutputStream arquivo;

            arquivo = new FileOutputStream("C:\\Users\\Edilson Jr\\Downloads\\Saapa\\src\\main\\webapp\\images\\graficos\\gordura\\" + pesquisado.getMatricula() + "gordura.png");
            ChartUtilities.writeChartAsPNG(arquivo, grafico, 550, 400);

            arquivo.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GraficoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GraficoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
