package OnlineBank.backend.queries;

public enum CoreQueries {

    DATABASE("online_bank"),

    ACCOUNTS("SELECT * FROM `customers` WHERE `id`=?"),

    ACCOUNT_QUERY("SELECT * FROM `customers` WHERE `uniqueId`=?"),
    ACCOUNT_INSERT("INSERT INTO `customers` (`uniqueId`, `name`, `bank`, `cardBrand`, `cardNumber`, `cardExpires`, `cardHolder`, `cardCVC`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);"),
    ACCOUNT_UPDATE("UPDATE `customers` SET `name`=?, `bank`=?, `cardBrand`=?, `cardNumber`=?, `cardExpires`=?, `cardHolder`=?, `cardCVC`=?, WHERE `id`=?;"),

    TABLE_ACCOUNTS("CREATE TABLE IF NOT EXISTS `" + DATABASE.toString() + "`.`customers` (`id` INT NOT NULL AUTO_INCREMENT, `uniqueId` VARCHAR(64) NOT NULL, `name` VARCHAR(64) NOT NULL, `bank` VARCHAR(64) NOT NULL, `cardBrand` VARCHAR(64) NOT NULL, `cardNumber` VARCHAR(64) NOT NULL, `cardExpires` LONG NOT NULL, `cardHolder` VARCHAR(64) NOT NULL, `cardCVC` INT NOT NULL, PRIMARY KEY (`id`, `uniqueId`), UNIQUE INDEX `id_UNIQUE` (`id` ASC)) ENGINE = InnoDB;"),

    LAST_ID("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='" + DATABASE.toString() + "' AND TABLE_NAME=?;");

    private final String query;

    private CoreQueries(String query) {
        this.query = query;
    }

    public String toString() {
        return query;
    }
}
