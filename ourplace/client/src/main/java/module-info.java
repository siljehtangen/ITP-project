module gr2310.ourplace.client {
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires transitive gr2310.ourplace.core;
    requires spring.web;
    requires spring.boot;
    requires spring.core;

    opens gr2310.ourplace.client to com.fasterxml.jackson.databind;

    exports gr2310.ourplace.client;
}
