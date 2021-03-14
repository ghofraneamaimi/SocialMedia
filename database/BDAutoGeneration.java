package database;
import models.*;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

public class BDAutoGeneration {

       // public static void main(String[] args) {
    public BDAutoGeneration(){
            Map<String, String> settings = new HashMap<>();
            settings.put("connection.driver_class", "org.postgresql.Driver");
            //hibernate.dialect: nom de la classe pleinement qualifié qui assure le dialogue avec la base de données
            settings.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            settings.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/SocialMedia");
            settings.put("hibernate.connection.username", "postgres");
            settings.put("hibernate.connection.password", "123456");
            settings.put("hibernate.hbm2ddl.auto", "create");
            settings.put("show_sql", "true");



            MetadataSources metadata = new MetadataSources(
                    new StandardServiceRegistryBuilder()
                            .applySettings(settings)
                            .build());
            for (Class clazz : new Class[] { Membre.class,MemberShip.class, Page.class, Aimes.class, Rejoindre.class, Groupe.class }) {
                metadata.addAnnotatedClass(clazz);
            }


            SchemaExport schemaExport = new SchemaExport();
            schemaExport.create(EnumSet.of( TargetType.DATABASE ), metadata.buildMetadata());



        }

    }

