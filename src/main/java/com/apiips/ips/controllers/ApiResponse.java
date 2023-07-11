package com.apiips.ips.controllers;

public class ApiResponse {
    
    private String message;
    private Object objeto;
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObjeto() {
        return objeto;
    }
    
    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
    
    public ApiResponse(String message, Object objeto) {
        this.message = message;
        this.objeto = objeto;
    }
    
    public ApiResponse(String message){
         this.message = message;
    }
    
    public ApiResponse(String message, boolean includeNullObjeto) {
        this.message = message;
        if (!includeNullObjeto) {
            this.objeto = new Object();
        }
    }

}

