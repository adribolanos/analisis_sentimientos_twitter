import com.google.cloud.language.v1.AnalyzeSentimentResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.opencsv.CSVReader;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;

import javax.xml.soap.Text;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class tweetsMapping extends Mapper<Object, Text, Text, IntWritable> {
    public static final String SEPARATOR = ",";

    public static void main(String[] args) throws IOException {
        ArrayList<String[]> texto = new ArrayList<String[]>();
        texto = analizarFeeling(leerDatos());
        ArrayList<String> imprimir= new ArrayList<String>();
        for(int i=0; i<texto.size(); i++) {
            imprimir.add(texto.get(0) + "," + texto.get(1));

        }
        csv(imprimir);
        //llamar al csv
    }


    public static ArrayList<String> leerDatos() throws IOException {
        ArrayList<String> texto = new ArrayList<String>();

        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader("C:\\Users\\Adri\\Desktop\\Upa\\BigData\\trabajos_finales\\Big Data\\resultadotweet.csv"));
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(SEPARATOR);
                if(fields.length==4) {
                    texto.add(fields[fields.length - 1]);
                    System.out.println(fields[fields.length - 1]);
                }

                line = br.readLine();
            }

        } catch (Exception e) {

        } finally {
            if (null != br) {
                br.close();
            }
        }
        return texto;
        //System.out.println(texto);
    }


    public static ArrayList<String[]> analizarFeeling(ArrayList<String> tweets) {

        ArrayList<String[]>result = new ArrayList<String[]>();
        String [] lista= new String [2];

        for (int i=0;i<tweets.size();i++) {

            //Instantiate the Language client com.google.cloud.language.v1.LanguageServiceClient
            try (LanguageServiceClient language = LanguageServiceClient.create()) {
                Document doc = Document.newBuilder()
                        .setContent(String.valueOf(tweets.get(i)))
                        .setType(Document.Type.PLAIN_TEXT)
                        .build();
                AnalyzeSentimentResponse response = language.analyzeSentiment(doc);
                Sentiment sentiment = response.getDocumentSentiment();
                lista[0] = tweets.get(i);
                lista[1] = String.valueOf(sentiment);
                result.add(lista);

                if (sentiment == null) {
                    System.out.println("No sentiment found");
                } else {
                    System.out.printf("Sentiment magnitude: %.3f\n", sentiment.getMagnitude());
                    System.out.printf("Sentiment score: %.3f\n", sentiment.getScore());
                }
                System.out.print(sentiment);


                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static void csv(ArrayList<String> lista){

        // If the file doesn't exists, create and write to it
        // If the file exists, truncate (remove all content) and write to it
        for(int i=0; i<lista.size(); i++ ) {
            try (FileWriter writer = new FileWriter("C:\\Users\\Adri\\Desktop\\Upa\\BigData\\trabajos_finales\\Big Data\\resultadomappingtweet.csv");
                 BufferedWriter bw = new BufferedWriter(writer)) {

                bw.write(lista.get(i));

            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        }

    }

 /*   @Override
   public void map(Object key, Text input, Context context){
        try {
            StringTokenizer tokenizer = new StringTokenizer(input.toString());
            while(tokenizer.hasMoreTokens()){
               Text word = new Text();
               word.set(tokenizer.nextToken());
               context.write(word, new IntWritable(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
*/
}


