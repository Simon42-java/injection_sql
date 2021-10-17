package injection.bdd.dampierre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {

    public List<Utilisateur> trouverParNom(String nom) {

        // initialisation de la base de donnée 
        Bdd bdd = new Bdd();
        //connection a la base de donnée
        Connection conn = bdd.connexion();

        //commande sql a eviter d'utiliser pour cause de injection sql
        //a cause du ('%s'",nom) cela et une concatenation et cela va causer une injection sql
        //String sql = String.format("select ID_USER, Prenom, Nom, Email from user where Nom = '%s'", nom);
        //voici la bonne commande pour eviter les concatenation avec le "?" donc eviter les injection sql 
        String sql = String.format ("select ID_USER, Prenom, Nom, Email from user where Nom = ?");
        List<Utilisateur> utilisateurs = new ArrayList<>();

        //essayer de crée un statement 
        try {
            //Statement stmt = conn.createStatement();
            //ResultSet res = stmt.executeQuery(sql);
            //cela peut eviter une ijection sql car il peut detecter les caractére spécieux avec le requet preparer
            PreparedStatement stmt = conn.prepareStatement(sql);
            //cette commande vas remplacer le ? par le 1 et lui ajouter le nom
            stmt.setString(1,nom);
            //pour eviter de mettre sql en parametre
            ResultSet res = stmt.executeQuery();
            //pour chaque res ajout un client
            while (res.next()) {
                utilisateurs.add(contruireClientDepuis(res));
            }
        // si il n'y arrive pas
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return utilisateurs;
    }

    private Utilisateur contruireClientDepuis(ResultSet res) throws SQLException {


        Long id = res.getLong("ID_User");
        String prenom = res.getString("Prenom");
        String nom = res.getString("Nom");
        String email = res.getString("Email");

        // appele de la methode Utilisateur avec pour parametre id prenom nom et email dans la class utilateur pour 
        Utilisateur utilisateur = new Utilisateur(id, prenom, nom, email);

        return utilisateur;
    }

}
