package com.learningstuff.stockexchange_application.service;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

@Service
public class TextFileServiceForDateHandling {

    public String getPreviousDateFromFile() {
        String date = null;
        try (RandomAccessFile file = new RandomAccessFile("previousDate.txt", "r")) {
            String line;
            while ((line = file.readLine()) != null) {
                date = line;
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return date;
    }

    public void saveCurrentDateIntoFile(String date) {
        try (RandomAccessFile file = new RandomAccessFile("previousDate.txt", "rw")) {
            file.setLength(0);

            file.writeBytes(date);

            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
