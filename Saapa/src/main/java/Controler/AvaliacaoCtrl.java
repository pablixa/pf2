package Controler;

import DAO.AlunoDao;
import DAO.AvaliacaoDao;
import Model.Aluno;
import Model.Avaliacao;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 
 */
public class AvaliacaoCtrl {

    Avaliacao avaliacao;
    AvaliacaoDao ad;

    public void adicionar(Avaliacao avaliacao) {
        ad = new AvaliacaoDao();
        Date d = new Date();
        avaliacao.setDataAtual(new Date(d.getYear(), d.getMonth(), d.getDate()));
        avaliacao.setDataRenovacao(new Date(d.getYear(), d.getMonth(), d.getDate()));
        try {
            ad.adicionar(avaliacao);
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean existe(String matricula, Date data) {
        ad = new AvaliacaoDao();
        int mat = Integer.parseInt(matricula);
        try {
            return ad.existe(mat, data);
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public List pesquisado(String matricula, String data) throws ParseException {
        ad = new AvaliacaoDao();
        int mat = Integer.parseInt(matricula);
        data = data.trim().replace(" ", "");
        data = data.replace(" ", "");
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);

        List<Avaliacao> list = null;
        try {
            list = ad.pesquisado(mat, date);
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void alterar(Avaliacao avaliacao) {
        ad = new AvaliacaoDao();
        Date d = new Date();
        avaliacao.setDataAtual(new Date(d.getYear(), d.getMonth(), d.getDate()));
        avaliacao.setDataRenovacao(new Date(d.getYear(), d.getMonth(), d.getDate()));
        try {
            ad.alterar(avaliacao);
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Double> geraImc(Aluno a) {
        List<Double> imcs = new ArrayList<Double>();
        double d = 0;
        AvaliacaoDao ctrl = new AvaliacaoDao();
        try {
            a.setAvaliacoes(ctrl.listarAvaliacoes(a));
            for (Avaliacao aval : a.getAvaliacoes()) {
                d = calculaImc(aval.getAltura(), aval.getPeso());
                imcs.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AvaliacaoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Controler.AlunoCtrl.geraImc()" + imcs.get(imcs.size() - 1));
        return imcs;
    }

    public double calculaImc(double altura, double peso) {
        double d = altura * altura;
        System.out.println("Controler.AlunoCtrl.calculaImc() imc: " + d);
        d = peso / d;
        System.out.println("Controler.AlunoCtrl.CalculaImc() imc: " + d);
        return d;
    }

    public String classificaImc(int matricula) throws SQLException {
        double avaldoub = 0;
        List<Double> imcs = new ArrayList<Double>();
        double d = 0;
        AlunoDao alunoDao = new AlunoDao();
        AvaliacaoDao ctrl = new AvaliacaoDao();
        Aluno a = (Aluno) alunoDao.pesquisado(matricula);
        try {
            a.setAvaliacoes(ctrl.listarAvaliacoes(a));
            for (Avaliacao aval : a.getAvaliacoes()) {
                d = calculaImc(aval.getAltura(), aval.getPeso());

                imcs.add(d);
            }

            avaldoub = imcs.get(imcs.size() - 1);

            if (avaldoub == 25) {
                return "Sobrepeso";
            } else if (avaldoub >= 18.5 && avaldoub < 25) {
                return "Normal";
            } else if (avaldoub > 25 && avaldoub < 30) {
                return "Pré-obeso";
            } else if (avaldoub > 30 && avaldoub < 35) {
                return "Obeso 1";
            } else if (avaldoub > 35 && avaldoub < 40) {
                return "Obeso 2 ";
            } else if (avaldoub >= 40) {
                return "Obeso 3 ";
            } else {
                return "abaixo do peso";
            }
        } catch (ParseException ex) {
            Logger.getLogger(AvaliacaoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Avaliacao> listarAvaliacoes(Aluno a) {
        ad = new AvaliacaoDao();
        try {
            List<Avaliacao> list = ad.listarAvaliacoes(a);
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AvaliacaoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public String classificaRcq(int matricula) throws SQLException {
        List<Double> imcs = new ArrayList<Double>();
        double d = 0;
        AlunoCtrl ac = new AlunoCtrl();
        AvaliacaoDao ctrl = new AvaliacaoDao();
        Aluno a = (Aluno) ac.pesquisado(matricula);
        try {
            a.setAvaliacoes(ctrl.listarAvaliacoes(a));
            for (Avaliacao aval : a.getAvaliacoes()) {
                d = calculaRcq(aval.getCintura(), aval.getQuadril());

                imcs.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AvaliacaoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        double avaliacao = imcs.get(imcs.size() - 1);

        int idade = ac.getIdade(a);
        System.out.println("Controler.AvaliacaoCtrl.classificaRcq()" + avaliacao + " = " + idade + "=" + a.getSexo());
        return medeRcq(avaliacao, a.getSexo(), idade);
    }

    List<Double> gerarRcq(Aluno pesquisado) {

        AvaliacaoDao ctrl = new AvaliacaoDao();
        List<Double> rcq = new ArrayList<Double>();
        try {
            pesquisado.setAvaliacoes(ctrl.listarAvaliacoes(pesquisado));
            for (Avaliacao aval : pesquisado.getAvaliacoes()) {
                double d = calculaRcq(aval.getCintura(), aval.getQuadril());

                rcq.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AvaliacaoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rcq;

    }

    public Double calculaRcq(double cintura, double quadril) {
        double d = cintura / quadril;
        return d;

    }

    String medeRcq(double rcq, String genero, int idade) {
        if (idade >= 20 && idade < 30) {
            if (genero.contains("m")) {
                if (rcq < 0.83) {
                    return "baixo";
                } else if (rcq >= 0.83 && rcq < 0.89) {
                    return "moderado";
                } else if (rcq >= 0.89 && rcq <= 0.94) {
                    return "alto";
                } else if (rcq > 0.94) {
                    return "muito alto";
                }
            }
            if (genero.contains("f")) {
                if (rcq < 0.71) {
                    return "baixo";
                } else if (rcq >= 0.71 && rcq < 0.77) {
                    return "moderado";
                } else if (rcq >= 0.78 && rcq < 0.82) {
                    return "alto";
                } else if (rcq > 0.82) {
                    return "mito alto";
                }
            }

        } else if (idade >= 30 && idade <= 39) {
            if (genero.contains("m")) {
                if (rcq < 0.84) {
                    return "baixo";
                } else if (rcq >= 0.84 && rcq < 0.92) {
                    return "moderado";
                } else if (rcq >= 0.92 && rcq <= 0.96) {
                    return "alto";
                } else if (rcq > 96) {
                    return "muito alto";
                }
            }
            if (genero.contains("f")) {
                if (rcq < 0.71) {
                    return "baixo";
                } else if (rcq >= 0.72 && rcq < 0.79) {
                    return "moderado";
                } else if (rcq >= 0.79 && rcq < 0.84) {
                    return "alto";
                } else if (rcq > 0.84) {
                    return "mito alto";
                }
            }
        } else if (idade >= 40 && idade <= 49) {
            if (genero.contains("m")) {
                if (rcq < 0.88) {
                    return "baixo";
                } else if (rcq >= 0.88 && rcq < 0.96) {
                    return "moderado";
                } else if (rcq >= 0.96 && rcq <= 1) {
                    return "alto";
                } else if (rcq > 1) {
                    return "muito alto";
                }
            }
            if (genero.contains("f")) {
                if (rcq < 0.73) {
                    return "baixo";
                } else if (rcq >= 0.73 && rcq < 0.80) {
                    return "moderado";
                } else if (rcq >= 0.80 && rcq < 0.87) {
                    return "alto";
                } else if (rcq > 0.87) {
                    return "mito alto";
                }
            }
        } else if (idade >= 50 && idade <= 59) {
            if (genero.contains("m")) {
                if (rcq < 0.90) {
                    return "baixo";
                } else if (rcq >= 0.90 && rcq <= 0.96) {
                    return "moderado";
                } else if (rcq >= 0.97 && rcq <= 1.02) {
                    return "alto";
                } else if (rcq > 1.02) {
                    return "muito alto";
                }
            }
            if (genero.contains("f")) {
                if (rcq < 0.74) {
                    return "baixo";
                } else if (rcq >= 0.74 && rcq < 0.82) {
                    return "moderado";
                } else if (rcq >= 0.82 && rcq < 0.88) {
                    return "alto";
                } else if (rcq > 0.88) {
                    return "mito alto";
                }
            }
        } else if (idade >= 60 && idade <= 69) {
            if (genero.contains("m")) {
                if (rcq < 0.91) {
                    return "baixo";
                } else if (rcq >= 0.91 && rcq <= 0.98) {
                    return "moderado";
                } else if (rcq >= 0.99 && rcq <= 1.03) {
                    return "alto";
                } else if (rcq > 1.03) {
                    return "muito alto";
                }
            }
            if (genero.contains("f")) {
                if (rcq < 0.76) {
                    return "baixo";
                } else if (rcq >= 0.76 && rcq < 0.84) {
                    return "moderado";
                } else if (rcq >= 0.84 && rcq <= 0.90) {
                    return "alto";
                } else if (rcq > 0.90) {
                    return "mito alto";
                }
            }
        }
        return "não foi possivel calcular risco metabólico ";

    }

    List<Double> gerarGordura(Aluno pesquisado) {
        AlunoCtrl ac = new AlunoCtrl();
        AvaliacaoDao ctrl = new AvaliacaoDao();
        List<Double> gordura = new ArrayList<Double>();
        try {
            pesquisado.setAvaliacoes(ctrl.listarAvaliacoes(pesquisado));
            for (Avaliacao aval : pesquisado.getAvaliacoes()) {
                double d = calculaGordura(aval, ac.getIdade(pesquisado));
                System.out.println("Controler.AvaliacaoCtrl.gerarGordura()" + d);
                gordura.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AvaliacaoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gordura;

    }
//this.form.d1_m.value = ((this.form.d1_1.value - 0) + (this.form.d1_2.value - 0) + (this.form.d1_3.value - 0)) / (3 - 0), this.form.d2_m.value = ((this.form.d2_1.value - 0) + (this.form.d2_2.value - 0) + (this.form.d2_3.value - 0)) / (3 - 0), this.form.d3_m.value = ((this.form.d3_1.value - 0) + (this.form.d3_2.value - 0) + (this.form.d3_3.value - 0)) / (3 - 0), this.form.d4_m.value = ((this.form.d4_1.value - 0) + (this.form.d4_2.value - 0) + (this.form.d4_3.value - 0)) / (3 - 0), this.form.d5_m.value = ((this.form.d5_1.value - 0) + (this.form.d5_2.value - 0) + (this.form.d5_3.value - 0)) / (3 - 0), this.form.d6_m.value = ((this.form.d6_1.value - 0) + (this.form.d6_2.value - 0) + (this.form.d6_3.value - 0)) / (3 - 0), this.form.d7_m.value = ((this.form.d7_1.value - 0) + (this.form.d7_2.value - 0) + (this.form.d7_3.value - 0)) / (3 - 0), st = (this.form.d1_m.value - 0) + (this.form.d2_m.value - 0) + (this.form.d3_m.value - 0) + (this.form.d4_m.value - 0) + (this.form.d5_m.value - 0) + (this.form.d6_m.value - 0) + (this.form.d7_m.value - 0), this.form.d.value = , this.form.g.value = (((4.95 - 0) / (this.form.d.value - 0)) - (4.5 - 0)) * (100 - 0), this.form.mg.value = (this.form.g.value - 0) * (this.form.peso.value - 0) / (100 - 0), this.form.mm.value = (this.form.peso.value - 0) - (this.form.mg.value - 0), this.form.pi.value = (this.form.mm.value - 0) / (this.form.perc.value - 0), this.form.obj.value = (this.form.peso.value - 0) - (this.form.pi.value - 0)

    private double calculaGordura(Avaliacao a, int idade) {
        double st = a.getAbdominal() + a.getCoxa() + a.getSubaxilar() + a.getSubescapular() + a.getTriceps() + a.getTorax() + a.getSuprailiaca();
        double st2 = st * st;
        double gordura = (1.112 - 0) - ((0.00043499 - 0) * (st - 0)) + ((0.00000055 - 0) * (st - 0) * (st - 0)) - ((0.0002882 - 0) * (idade - 0));
        System.out.println("Controler.AvaliacaoCtrl.calculaGordura()" + st);
        System.out.println("Controler.AvaliacaoCtrl.calculaGordura()" + idade);
        System.out.println("Controler.AvaliacaoCtrl.calculaGordura()" + gordura);
        gordura = (((4.95 - 0) / (gordura - 0)) - (4.5 - 0)) * (100 - 0);
        return gordura;
    }

    public String classificaGordura(int matricula) {

        double d = 0;
        AlunoCtrl ac = new AlunoCtrl();
        AvaliacaoDao ctrl = new AvaliacaoDao();
        Aluno a = (Aluno) ac.pesquisado(matricula);
        List<Double> gordura = new ArrayList<Double>();
        try {
            a.setAvaliacoes(ctrl.listarAvaliacoes(a));
            for (Avaliacao aval : a.getAvaliacoes()) {
                d = calculaGordura(aval, ac.getIdade(a));

                gordura.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AvaliacaoCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        double avaliacao = gordura.get(gordura.size() - 1);

        int idade = ac.getIdade(a);
        if (idade > 17 && idade < 26) {
            if (a.getSexo().equals("m")) {
                if (avaliacao > 4 && avaliacao < 7) {
                    return "Excelente";
                } else if (avaliacao > 7 && avaliacao < 12) {
                    return "Bom";
                } else if (avaliacao > 11 && avaliacao < 14) {
                    return "Acima da Média";
                } else if (avaliacao > 13 && avaliacao < 17) {
                    return "Media";
                } else if (avaliacao > 16 && avaliacao < 20) {
                    return "Abaixo da Média";
                } else if (avaliacao > 19 && avaliacao < 25) {
                    return "Ruim";
                } else if (avaliacao > 24) {
                    return "Muito Ruim";
                }
            }
            if (a.getSexo().equals("f")) {
                if (avaliacao > 13 && avaliacao < 17) {
                    return "Excelente";
                } else if (avaliacao > 16 && avaliacao < 21) {
                    return "Bom";
                } else if (avaliacao > 20 && avaliacao < 24) {
                    return "Acima da Média";
                } else if (avaliacao > 23 && avaliacao < 26) {
                    return "Media";
                } else if (avaliacao > 25 && avaliacao < 29) {
                    return "Abaixo da Média";
                } else if (avaliacao > 28) {
                    return "Ruim";
                } else if (avaliacao > 33) {
                    return "Muito Ruim";
                }
            }
        }
        if (idade > 25 && idade < 6) {
            if (a.getSexo().equals("m")) {
                if (avaliacao >= 8 && avaliacao < 12) {
                    return "Excelente";
                } else if (avaliacao > 11 && avaliacao < 16) {
                    return "Bom";
                }
                if (avaliacao > 15 && avaliacao < 19) {
                    return "Acima da Média";
                }
                if (avaliacao > 18 && avaliacao < 21) {
                    return "Media";
                }
                if (avaliacao > 20 && avaliacao < 25) {
                    return "Abaixo da Média";
                }
                if (avaliacao > 24 && avaliacao < 29) {
                    return "Ruim";
                }
                if (avaliacao > 28) {
                    return "Muito Ruim";
                }
            }
            if (a.getSexo().equals("f")) {
                if (avaliacao > 13 && avaliacao < 17) {
                    return "Excelente";
                } else if (avaliacao > 16 && avaliacao < 21) {
                    return "Bom";
                } else if (avaliacao > 20 && avaliacao < 22) {
                    return "Acima da Média";
                } else if (avaliacao > 23 && avaliacao < 26) {
                    return "Media";
                } else if (avaliacao > 25 && avaliacao < 29) {
                    return "Abaixo da Média";
                } else if (avaliacao > 28 && avaliacao < 34) {
                    return "Ruim";
                } else if (avaliacao > 33) {
                    return "Muito Ruim";
                }
            }
        }
        if (idade > 35 && idade < 46) {
            if (a.getSexo().equals("m")) {
                if (avaliacao >= 10 && avaliacao < 15) {
                    return "Excelente";
                } else if (avaliacao > 14 && avaliacao < 19) {
                    return "Bom";
                }
                if (avaliacao > 18 && avaliacao < 22) {
                    return "Acima da Média";
                }
                if (avaliacao > 21 && avaliacao < 24) {
                    return "Media";
                }
                if (avaliacao > 23 && avaliacao < 26) {
                    return "Abaixo da Média";
                }
                if (avaliacao > 25 && avaliacao < 30) {
                    return "Ruim";
                }
                if (avaliacao > 30) {
                    return "Muito Ruim";
                }
            }
            if (a.getSexo().equals("f")) {
                if (avaliacao > 15 && avaliacao < 20) {
                    return "Excelente";
                } else if (avaliacao > 19 && avaliacao < 24) {
                    return "Bom";
                } else if (avaliacao > 23 && avaliacao < 27) {
                    return "Acima da Média";
                } else if (avaliacao > 26 && avaliacao < 30) {
                    return "Media";
                } else if (avaliacao > 29 && avaliacao < 33) {
                    return "Abaixo da Média";
                } else if (avaliacao > 32 && avaliacao < 37) {
                    return "Ruim";
                } else if (avaliacao > 36) {
                    return "Muito Ruim";
                }
            }
        }
        if (idade > 45 && idade < 56) {
            if (a.getSexo().equals("m")) {
                if (avaliacao >= 11 && avaliacao < 17) {
                    return "Excelente";
                } else if (avaliacao > 16 && avaliacao < 21) {
                    return "Bom";
                }
                if (avaliacao > 20 && avaliacao < 24) {
                    return "Acima da Média";
                }
                if (avaliacao > 23 && avaliacao < 26) {
                    return "Media";
                }
                if (avaliacao > 25 && avaliacao < 28) {
                    return "Abaixo da Média";
                }
                if (avaliacao > 27 && avaliacao < 31) {
                    return "Ruim";
                }
                if (avaliacao > 31) {
                    return "Muito Ruim";
                }
            }
            if (a.getSexo().equals("f")) {
                if (avaliacao > 16 && avaliacao < 22) {
                    return "Excelente";
                } else if (avaliacao > 21 && avaliacao < 26) {
                    return "Bom";
                } else if (avaliacao > 25 && avaliacao < 29) {
                    return "Acima da Média";
                } else if (avaliacao > 28 && avaliacao < 32) {
                    return "Media";
                } else if (avaliacao > 31 && avaliacao < 35) {
                    return "Abaixo da Média";
                } else if (avaliacao > 34 && avaliacao < 39) {
                    return "Ruim";
                } else if (avaliacao > 38) {
                    return "Muito Ruim";
                }
            }
        }
        if (idade > 55 && idade < 66) {
            if (a.getSexo().equals("m")) {
                if (avaliacao >= 12 && avaliacao < 19) {
                    return "Excelente";
                } else if (avaliacao > 18 && avaliacao < 22) {
                    return "Bom";
                }
                if (avaliacao > 21 && avaliacao < 24) {
                    return "Acima da Média";
                }
                if (avaliacao > 23 && avaliacao < 25) {
                    return "Media";
                }
                if (avaliacao > 25 && avaliacao < 28) {
                    return "Abaixo da Média";
                }
                if (avaliacao > 27 && avaliacao < 31) {
                    return "Ruim";
                }
                if (avaliacao > 32) {
                    return "Muito Ruim";
                }
            }
            if (a.getSexo().equals("f")) {
                if (avaliacao > 17 && avaliacao < 23) {
                    return "Excelente";
                } else if (avaliacao > 21 && avaliacao < 26) {
                    return "Bom";
                } else if (avaliacao > 25 && avaliacao < 29) {
                    return "Acima da Média";
                } else if (avaliacao > 28 && avaliacao < 32) {
                    return "Media";
                } else if (avaliacao > 31 && avaliacao < 35) {
                    return "Abaixo da Média";
                } else if (avaliacao > 34 && avaliacao < 39) {
                    return "Ruim";
                } else if (avaliacao > 38) {
                    return "Muito Ruim";
                }
            }
        } else {
            return "nao foi possivel calcular.";
        }
        return "nao foi possivel calcular.";
    }
}
