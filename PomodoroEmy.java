// 🌸 Projeto desenvolvido em Java Swing
// 🍓 Pomodoro da emy


package com.mycompany.pomodoroemy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PomodoroEmy extends JFrame {

    // ⏰ Labels principais da interface
    private final JLabel timerLabel;
    private final JLabel contadorLabel;
    private JLabel marcadorLabel;

    // 🎀 Controle do timer
    private Timer timer;
    private int tempoRestante;
    private int tempoInicial;

    // 🍅 Contador de pomodoros concluídos
    private int pomodorosConcluidos = 0;

    // 🌸 Construtor principal da janela
    public PomodoroEmy() {

        // 💖 Configurações da janela
        setTitle("Pomodoro Fofo ♡");
        setSize(420, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 🎨 Fundo rosinha
        getContentPane().setBackground(new Color(255, 228, 236));
        setLayout(new BorderLayout());

        // 🍓 Título principal
        JLabel titulo = new JLabel(" Pomodoro da Emy <3 ", SwingConstants.CENTER);
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
        titulo.setForeground(new Color(255, 105, 180));

        // ⏰ Relógio principal
        timerLabel = new JLabel("25:00", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 52));
        timerLabel.setForeground(new Color(219, 112, 147));

        // 🍅 Contador diário
        contadorLabel = new JLabel("Pomodoros hoje: 0 <3", SwingConstants.CENTER);
        contadorLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        contadorLabel.setForeground(new Color(255, 105, 180));

        // 🌸 Frases motivacionais
        marcadorLabel = new JLabel(" Pronta para divar! ", SwingConstants.CENTER);
        marcadorLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        marcadorLabel.setForeground(new Color(199, 21, 133));

        // 💻 Painel central
        JPanel centro = new JPanel(new GridLayout(3, 1));
        centro.setBackground(new Color(255, 228, 236));
        centro.add(timerLabel);
        centro.add(contadorLabel);
        centro.add(marcadorLabel);

        // 🎀 Painel dos botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(new Color(255, 228, 236));

        // 🌷 Botões principais
        JButton estudoBtn = criarBotao(" Estudo ");
        JButton pausaCurtaBtn = criarBotao(" Pausa Curta ");
        JButton pausaLongaBtn = criarBotao(" Pausa Longa ");

        // 🚀 Ação: modo estudo
        estudoBtn.addActionListener(e -> {
            marcadorLabel.setText(" Modo foco ativado! ");
            iniciarTimer(25 * 60);
        });

        // ☕ Ação: pausa curta
        pausaCurtaBtn.addActionListener(e -> {
            marcadorLabel.setText(" Hora de uma pausa curtinha! ");
            iniciarTimer(5 * 60);
        });

        // 🌸 Ação: pausa longa
        pausaLongaBtn.addActionListener(e -> {
            marcadorLabel.setText(" Hora do descanso! ");
            iniciarTimer(15 * 60);
        });

        // 🍓 Adicionando botões
        painelBotoes.add(estudoBtn);
        painelBotoes.add(pausaCurtaBtn);
        painelBotoes.add(pausaLongaBtn);

        // 💕 Adicionando componentes na tela
        add(titulo, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    // 🎀 Método para criar botões fofinhos
    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        botao.setBackground(new Color(255, 182, 193));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        return botao;
    }

    // ⏰ Método responsável por iniciar o cronômetro
    private void iniciarTimer(int segundos) {

        // 🛑 Para timer anterior se estiver rodando
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        tempoRestante = segundos;
        tempoInicial = segundos;

        // 💻 Timer principal
        timer = new Timer(1000, (ActionEvent e) -> {
            // ⏳ Conversão para minutos e segundos
            int minutos = tempoRestante / 60;
            int seg = tempoRestante % 60;
            
            // 🕒 Atualiza relógio
            timerLabel.setText(String.format("%02d:%02d", minutos, seg));
            
            tempoRestante--;
            
            // ✨ Quando o tempo termina
            if (tempoRestante < 0) {
                timer.stop();
                
                // 🍅 Conta apenas ciclos de estudo
                if (tempoInicial == 25 * 60) {
                    pomodorosConcluidos++;
                    contadorLabel.setText("Pomodoros hoje: " + pomodorosConcluidos + " <3");
                    marcadorLabel.setText(" Mais um pomodoro concluído! ");
                } else {
                    marcadorLabel.setText(" Pausa finalizada! Bora continuar!");
                }
                
                // 🔔 Som
                Toolkit.getDefaultToolkit().beep();
                
                // 🌸 Mensagem final
                JOptionPane.showMessageDialog(
                        null,
                        " Tempo finalizado! ",
                        " Pomodoro Fofo ",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        // 🚀 Inicia contagem
        timer.start();
    }

    // 🌟 Método principal
    public static void main(String[] args) {

        // 💖 Inicializa app
        SwingUtilities.invokeLater(() -> {
            new PomodoroEmy().setVisible(true);
        });
    }
}