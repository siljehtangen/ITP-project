module gr2310.ourplace.core {
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires spring.web;
    requires spring.boot;
    requires spring.core;

    opens gr2310.ourplace.core to com.fasterxml.jackson.databind;

    exports gr2310.ourplace.core;
    exports gr2310.ourplace.json;
}
