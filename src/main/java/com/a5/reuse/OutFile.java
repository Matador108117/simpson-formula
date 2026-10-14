package com.a5.reuse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.List;
public class OutFile {
    // en tabla
    public void writeResultFile(List<String> list, String path, List<String> resultHeaders){
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(path))) {
            String[] headers = resultHeaders.toArray(new String[0]);
            String[] values = list.toArray(new String[0]);
            int columns = Math.max(headers.length, values.length);
            int[] widths = new int[columns];

            for (int i = 0; i < columns; i++) {
                String header = (i < headers.length) ? headers[i] : "";
                String value = (i < values.length) ? values[i] : "";
                widths[i] = Math.max(header.length(), value.length());
            }

            for (int i = 0; i < columns; i++) {
                String header = (i < headers.length) ? headers[i] : "";
                wr.write(String.format("%-" + widths[i] + "s", header));
                if (i < columns - 1) {
                    wr.write("  ");
                }
            }
            wr.newLine();
            wr.newLine();

            for (int i = 0; i < columns; i++) {
                String value = (i < values.length) ? values[i] : "";
                wr.write(String.format("%-" + widths[i] + "s", value));
                if (i < columns - 1) {
                    wr.write("  ");
                }
            }
            wr.newLine();
        } catch(IOException e){
          throw new RuntimeException(e);
        }
    }
}
