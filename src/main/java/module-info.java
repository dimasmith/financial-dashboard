module net.anatolich.finance {
    requires javafx.controls;
    requires javafx.fxml;
    requires slf4j.api;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires net.bytebuddy;
    requires java.persistence;
    requires com.fasterxml.classmate;
    requires java.xml.bind;
    requires java.naming;
    requires org.apache.commons.configuration2;
    requires commons.beanutils;
    requires org.flywaydb.core;

    opens net.anatolich.finance to javafx.fxml;
    opens net.anatolich.finance.cards.domain to org.hibernate.orm.core;
    opens db.migration;
    exports net.anatolich.finance;
}
