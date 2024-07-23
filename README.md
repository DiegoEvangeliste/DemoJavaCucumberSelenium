# Testing con Cucumber y Selenium

Proyecto demo base que implementa test automatizados con Cucumber y Selenium.

Todo el material base se extrajo del curso de Udemy **"Selenium con Java y Cucumber"** de **FreeRangeTesters**, y fue
modificado por requerimientos de actualización

---

## Nota

Para que los tests corran se debe tener instalado el navegador *firefox*, en caso de no tenerlo hay que configurar en la clase **BasePage.class** dentro del metodo *static* las configuraciones del driver.

Si se usa *firefox* y no anda, se le debe dar permiso a *ghekoDriver*. Esto en linux se hace desde la terminal ingresando el siguiente comando:`sudo chmod +x /RUTA_DONDE_ESTA_GECKO_DRIVER/geckodriver`