package com.agenda_telefonica.contactos.records;

import java.util.Optional;

public record JsonContactoPost(String name, String phone, Optional<String> codArea) { }
