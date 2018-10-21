package pl.emgie.ocaexamquestionsapp.questions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface BaseService {
    default Logger getLogger() {
        return LoggerFactory.getLogger(getClass());
    }
}