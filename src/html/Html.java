package html;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Html {
    
    public static String[] strFile=new String[11];
    
    public  static int  Read(String filename)throws Exception{
         File file = new File(filename);
         if(!file.exists()){
            System.out.println("Ошибка, файла info.properties не существует");
           return 1;
         }
        
        FileReader fr = new FileReader( filename );
        Scanner scan = new Scanner(fr);
        for (int i=0;scan.hasNextLine()&&i<11;i++)            
            strFile[i]=scan.nextLine();
        fr.close();
        return 0;
    }
    
    public static int Write(String filename)throws Exception{
        FileWriter fw = new FileWriter( filename );
              
        String str = "<!DOCTYPE html>\n<html>\n	<head>\n		<meta charset=\"utf-8\">\n		<title>Резюме</title>\n		<style>\n			.col { \n				width: 1200px;\n				word-wrap: break-word;\n			}\n		</style>\n		<p align=\"center\"><font size='15'>Резюме</font><br><font size='5'>На должность Java-стажер</font></p>\n	</head>\n	<body>\n		<div class=\"col\"> <p> <strong>ФИО: </strong>";
        fw.write(str);
        
        File file = new File(filename);
         if(!file.exists()){
            System.out.println("Ошибка создания файла html");
            return 1;
         }
         fw.close();
         return 0;
    }
    
    public static void Html()throws Exception{
      Write("Rez.html");                                            //Создание html страницы
      
      //Блок о себе
      Add(strFile[0],"Rez.html");              //строка ФИО
      Add("<br><strong>Дата рождения:</strong> ","Rez.html");       
      Add(strFile[1],"Rez.html");                               //строка дата рождения
      Add("<br><strong>Телефон:</strong> ","Rez.html");
      Add(strFile[2],"Rez.html");                               //телефон
      Add("<br><strong>e-mail</strong> ","Rez.html");
      Add(strFile[3],"Rez.html");                               //e-mail
      
      //Аватар
      Add("<img src=","Rez.html");                                  
      Add(strFile[4],"Rez.html");
      Add(" width=\"200\" height=\"200\"alt=\"аватар\" align=\"right\">","Rez.html");
      
      //Цель
      Add("<br><br><br><br><br><br><br><br><br><br><br><br><strong><font size='6'>Цель:</font></strong><br>","Rez.html"); 
      Add(strFile[5],"Rez.html");
      
      //Опыт
      Add("<br><strong><font size='6'>Опыт:</font></strong><br>","Rez.html");
      Add(strFile[6],"Rez.html");
      
      //Образование
      Add("<br><strong><font size='6'>Образование:</font><br></strong>","Rez.html");
      Add(strFile[7],"Rez.html");
      
      //Курсы
      Add("<br><strong><font size='6'>Курсы:</font></strong><br>","Rez.html");
      Add(strFile[8],"Rez.html");
      
      //Навыки
      Add("<br><strong><font size='6'>Навыки:</font></strong><br>","Rez.html");
      Add(strFile[9],"Rez.html");
      
      //Пример
      Add("<br><strong><font size='6'>Пример кода:</font><br></strong> ","Rez.html");
      Add(strFile[10],"Rez.html");
      
      Add("</p >\n		</div>\n	</body>\n</html>","Rez.html");              //конец страницы
      
      System.out.println("Успешное выполнение");
    }
    
    public static void Add(String str,String filename)throws Exception{
         try{
            FileWriter sw = new FileWriter(filename,true);
                sw.write(str);
                sw.close();
         }catch(Exception e){
             System.out.print(e.getMessage());
         }    
    }
    
    public static int Trans()throws Exception{
        String strProg[]={
            "FIO=",
            "DOB=",
            "Phone=",
            "E-mail=",
            "Avatar=",
            "Target=",
            "Experience=",
            "Education=",
            "Courses=",
            "Skills=",
            "Example="                
        };
        strFile[0]=strFile[0].substring(1);
        int t;
        for(int i=0;i<11;i++)
            if(strFile[i].startsWith(strProg[i])){
               t=strFile[i].indexOf("=");
               strFile[i]=strFile[i].substring(t+1);
            }
            else{
                System.out.println("Ошибка файла info.properties в строке "+(i+1));
                return 1;
            }
        return 0;
    }
    
    public static void main(String[] args)throws Exception {
        Read("info.properties");        //Считать данные
        Trans();                        //Проверка и преобразование
        Html();                         //Сгенерировать html-файл
    }
    
}
