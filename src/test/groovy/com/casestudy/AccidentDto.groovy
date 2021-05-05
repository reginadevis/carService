package com.casestudy

import lombok.Data

@Data
class AccidentDto {

    Integer vin;

    Date accidentDate;

    String description;
}
