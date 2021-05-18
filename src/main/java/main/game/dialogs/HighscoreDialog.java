package main.game.dialogs;

import main.game.savedata.HighscoreLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HighscoreDialog extends JDialog implements ActionListener {

    public JFrame parent;

    public HighscoreDialog(JFrame parent) {
        this.parent = parent;
    }

    public LinkedHashMap<String, Integer> sortHighscores(HashMap<String, Integer> higscores) {
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        higscores.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        return sortedMap;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringBuilder sb = new StringBuilder();
        try {
            LinkedHashMap<String, Integer> highscores = sortHighscores(HighscoreLoader.getScores());
            if(highscores.size() < 1) throw new Exception("No Scores Existing");
            sb.append("<html><body><table border=1>");
            sb.append("<tr><th>Name</th><th>Score</th></tr>");
            int count = 0;
            for(Map.Entry<String, Integer> entry : highscores.entrySet()) {
                count++;
                sb.append("<tr>");
                sb.append("<td>" + entry.getKey() + "</td>");
                sb.append("<td>" + entry.getValue() + "</td>");
                sb.append("</tr>");
                if(count == 10) break;
            }
            sb.append("</table></body></html>");
            JOptionPane.showMessageDialog(this, sb.toString(), "Deine Top Scores", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ganz schön leer hier...", "Deine Top Scores", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}