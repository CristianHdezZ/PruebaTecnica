package co.cobre.lib.qa.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.cobre.lib.qa.model.RecaudoTemplate;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import java.io.Reader;

public class UpdateFileCSV {

    public static List<RecaudoTemplate> cargarDatosRecaudos(String strPath, String strNumeroDocumento, String strFechaVencimiento){

        List<RecaudoTemplate> recaudoTemplates = new ArrayList<>();
        Path rutaFile = Paths.get(strPath);
        try{
            Reader reader = Files.newBufferedReader(rutaFile);
            CSVFormat csvFormat = CSVFormat.DEFAULT
                    .withHeader("tipoDocumento","numeroDocumento","nombrePagador","celular","email","referenciaUna","referenciaDos","descripcion","monto","fechaVencimiento","url")
                    .withDelimiter(';');

            CSVParser csvParser = new CSVParser(reader,csvFormat);

            RecaudoTemplate recaudoTemplate;

            List<CSVRecord> registros = csvParser.getRecords().stream().filter(r-> r.getRecordNumber()!=1)
                    .collect(Collectors.toList());

            for (CSVRecord r : registros) {
                recaudoTemplate = new RecaudoTemplate(
                        r.get("tipoDocumento"),
                        r.get("numeroDocumento"),
                        r.get("nombrePagador"),
                        r.get("celular"),
                        r.get("email"),
                        r.get("referenciaUna"),
                        r.get("referenciaDos"),
                        r.get("descripcion"),
                        r.get("monto"),
                        r.get("fechaVencimiento"),
                        r.get("url"));
//                recaudoTemplate.setFechaVencimiento(strFechaVencimiento);

                recaudoTemplates.add(recaudoTemplate);
            }

//            writeCSV(rutaFile.toString(), recaudoTemplates,csvFormat);

            for (RecaudoTemplate r: recaudoTemplates) {
                if (r.getNumeroDocumento().equals(strNumeroDocumento)){
                    System.out.println("Recaudo: "+r.getNumeroDocumento()+" - "
                                                  +r.getNombrePagador()+" - "
                                                  +r.getCelular()+" - "
                                                  +r.getFechaVencimiento());

                    r.setFechaVencimiento(strFechaVencimiento);
                    writeCSV(rutaFile.toString(), recaudoTemplates,csvFormat);
                }

            }

            csvParser.close();

        }catch(Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }

        return recaudoTemplates;
    }


    public static void writeCSV(String filePath, List<RecaudoTemplate> records, CSVFormat format) throws IOException {

        try (Writer writer = Files.newBufferedWriter(Paths.get(filePath));

             CSVPrinter csvPrinter = new CSVPrinter(writer, format)) {

            for (RecaudoTemplate record : records) {
                String rowRecord = record.getTipoDocumento()+";"
                        +record.getNumeroDocumento()+";"
                        +record.getNombrePagador()+";"
                        +record.getCelular()+";"
                        +record.getEmail()+";"
                        +record.getReferenciaUna()+";"
                        +record.getReferenciaDos()+";"
                        +record.getDescripcion()+";"
                        +record.getMonto()+";"
                        +record.getFechaVencimiento()+";"
                        +record.getUrl();

                CSVParser csvParser = CSVParser.parse(rowRecord,format);
                CSVRecord recordRow = csvParser.getRecords().get(0);
                csvPrinter.printRecord(recordRow);
            }
        }
    }






}
