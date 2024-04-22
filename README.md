# Ali - Test

## Fremgangsmåde

Jeg har tilføjet tests til følgende klasser:
* Student
* PrefectService




### Student

I Student-klassen har jeg tilføjet en ny boolean attribut, prefect, som har en getter og setter. Til setteren har jeg skrevet tests for at kontrollere, om 
de kan tilskrives som prefect i overensstemmelse med et schoolyear-krav. Derudover er der også tilføjet attributten Gender som anvendes ifm. tilskrivelse af 
prefect inden for et House.

Jeg har testet edge-cases, da det gav mest mening. Navngivningen på testene er 
**makeFifthYearStudentPrefect()** og **makeFourthYearStudentPrefect()**  


### PrefectService

I PrefectService-klassen har testing været mere omstændigt, da der skulle mockes repository-laget og StudentService for at tilgå **toDTO()** metoden ifm. 
return. Ved "unhappy path" har jeg valgt at kaste exceptions, som returnerer en passende 4xx http status code fejl i controller-laget. Disse exceptions 
bliver der også taget højde for i mine tests af PrefectService, hvor jeg anvender **assertThrows()**.

Testene kan findes i **PrefectServiceTest** klassen.



======
======

##### Ali Haider Mohammad, 22/04/2024