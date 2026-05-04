//  projeto desenvolvido em Java Swing
//  pomodoro da emy


package com.mycompany.pomodoroemy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PomodoroEmy extends JFrame {

    //  labels da interface
    private final JLabel timerLabel;
    private final JLabel contadorLabel;
    private JLabel marcadorLabel;

    //  controle do timer
    private Timer timer;
    private int tempoRestante;
    private int tempoInicial;

    //  contador de pomodoros concluídos
    private int pomodorosConcluidos = 0;

    //  construtor principal da janela
    public PomodoroEmy() {

        //  configurações da janela
        setTitle("Pomodoro Fofo ♡");
        setSize(420, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //  fundo rosinha
        getContentPane().setBackground(new Color(255, 228, 236));
        setLayout(new BorderLayout());

        //  título principal
        JLabel titulo = new JLabel(" Pomodoro da Emy <3 ", SwingConstants.CENTER);
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
        titulo.setForeground(new Color(255, 105, 180));

        // relógio principal
        timerLabel = new JLabel("25:00", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 52));
        timerLabel.setForeground(new Color(219, 112, 147));

        // contador diário
        contadorLabel = new JLabel("Pomodoros hoje: 0 <3", SwingConstants.CENTER);
        contadorLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        contadorLabel.setForeground(new Color(255, 105, 180));

        // frases motivacionais
        marcadorLabel = new JLabel(" Pronta para divar! ", SwingConstants.CENTER);
        marcadorLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        marcadorLabel.setForeground(new Color(199, 21, 133));

        // painel central
        JPanel centro = new JPanel(new GridLayout(3, 1));
        centro.setBackground(new Color(255, 228, 236));
        centro.add(timerLabel);
        centro.add(contadorLabel);
        centro.add(marcadorLabel);

        // painel dos botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(new Color(255, 228, 236));

        // botões principais
        JButton estudoBtn = criarBotao(" Estudo ");
        JButton pausaCurtaBtn = criarBotao(" Pausa Curta ");
        JButton pausaLongaBtn = criarBotao(" Pausa Longa ");

        // ação: modo estudo
        estudoBtn.addActionListener(e -> {
            marcadorLabel.setText(" Modo foco ativado! ");
            iniciarTimer(25 * 60);
        });

        // ação: pausa curta
        pausaCurtaBtn.addActionListener(e -> {
            marcadorLabel.setText(" Hora de uma pausa curtinha! ");
            iniciarTimer(5 * 60);
        });

        // ação: pausa longa
        pausaLongaBtn.addActionListener(e -> {
            marcadorLabel.setText(" Hora do descanso! ");
            iniciarTimer(15 * 60);
        });

        // adicionando botões
        painelBotoes.add(estudoBtn);
        painelBotoes.add(pausaCurtaBtn);
        painelBotoes.add(pausaLongaBtn);

        // adicionando componentes na tela
        add(titulo, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    // método para criar botões fofinhos
    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        botao.setBackground(new Color(255, 182, 193));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        return botao;
    }

    // método responsável por iniciar o cronômetro
    private void iniciarTimer(int segundos) {

        // para timer anterior se estiver rodando
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        tempoRestante = segundos;
        tempoInicial = segundos;

        // timer principal
        timer = new Timer(1000, (ActionEvent e) -> {
            // ⏳ Conversão para minutos e segundos
            int minutos = tempoRestante / 60;
            int seg = tempoRestante % 60;
            
            // atualiza relógio
            timerLabel.setText(String.format("%02d:%02d", minutos, seg));
            
            tempoRestante--;
            
            // quando o tempo termina
            if (tempoRestante < 0) {
                timer.stop();
                
                // conta apenas ciclos de estudo
                if (tempoInicial == 25 * 60) {
                    pomodorosConcluidos++;
                    contadorLabel.setText("Pomodoros hoje: " + pomodorosConcluidos + " <3");
                    marcadorLabel.setText(" Mais um pomodoro concluído! ");
                } else {
                    marcadorLabel.setText(" Pausa finalizada! Bora continuar!");
                }
                
                // som
                Toolkit.getDefaultToolkit().beep();
                
                // mensagem final
                JOptionPane.showMessageDialog(
                        null,
                        " Tempo finalizado! ",
                        " Pomodoro Fofo ",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        // inicia contagem
        timer.start();
    }

    // método principal
    public static void main(String[] args) {

        // 💖 Inicializa app
        SwingUtilities.invokeLater(() -> {
            new PomodoroEmy().setVisible(true);
        });
    }
}
