/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adopet.utils;

import adopet.model.entity.Foto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author leticia
 */
public class IOUtils {

    /**
     * Método responsável por converter o arquivo no formato MultiPartFile para
     * um File.
     *
     *
     * @param file
     * @return
     */
    public static File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
            return convFile;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Método responsável por converter o arquivo no formato MultiPartFile para
     * um File.
     *
     * @param multipart arquivo multipart
     * @return conteudo file
     * @throws IllegalStateException
     * @throws IOException
     */
    public static File multipartToFile(MultipartFile multipart) {
        try {
            File convFile = new File(multipart.getOriginalFilename());
            multipart.transferTo(convFile);
            return convFile;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Método responsável por criar uma arquivo no diretório desejado.
     *
     * @param caminho caminho onde será salvo o arquivo.
     * @param nome nome do arquivo.
     * @param file conteudo do arquivo.
     * @return nome do arquivo caso esteja salvo no diretorio desejado.
     */
    public static String createFile(String caminho, String nome, MultipartFile file) {
        try {

            FileOutputStream fos = new FileOutputStream(caminho + nome);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException io) {
            io.printStackTrace();
            return null;
        }
        return nome;
    }

    public static byte[] readFile(String nome) {
        String caminho = ConfiguracaoSistema.caminhoImagem;
        File file = new File(caminho + nome);
        if (file != null && file.exists()) {
            byte[] byteArray = new byte[(int) file.length()];
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(byteArray);
                for (int i = 0; i < byteArray.length; i++) {
                    System.out.print((char) byteArray[i]);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found.");
                e.printStackTrace();
            } catch (IOException e1) {
                System.out.println("Error Reading The File.");
                e1.printStackTrace();
            }
            return byteArray;
        }
        return null;
    }
}
