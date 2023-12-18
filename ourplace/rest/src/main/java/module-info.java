module gr2310.ourplace.rest {
    requires transitive gr2310.ourplace.core;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;

    opens gr2310.ourplace.rest to spring.core, spring.beans, spring.context, spring.test, spring.web, com.fasterxml.jackson.databind;

    exports gr2310.ourplace.rest;
    // exports gr2310.ourplace;
}
