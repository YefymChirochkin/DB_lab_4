package labwork5;

import labwork5.mvc.view.View;

import java.sql.SQLException;


public class App {

    public static void main(String[] args) {

        try {

            new View().show();

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

    }

}
