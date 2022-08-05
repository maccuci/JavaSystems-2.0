package OnlineBank.backend.queries;

public enum BankSqlQueries {

    DATABASE("online_bank"),

    ACCOUNTS("SELECT * FROM `customers` WHERE `id`=?"),

    CUSTOMER_QUERY("SELECT * FROM `customers` WHERE `uniqueId`=?"),
    CUSTOMER_INSERT("INSERT INTO `customers` (`uniqueId`, `name`, `bank`, `cardBrand`, `cardNumber`, `cardExpires`, `cardHolder`, `cardCVC`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);"),
    CUSTOMER_UPDATE("UPDATE `customers` SET `name`=?, `bank`=?, `cardBrand`=?, `cardNumber`=?, `cardExpires`=?, `cardHolder`=?, `cardCVC`=?, WHERE `id`=?;"),

    BANK_QUERY("SELECT * FROM `banks` WHERE `name`=?"),
    BANK_INSERT("INSERT INTO `banks` (`uniqueId`, `name`, `brand`, `customers`, `cards`) VALUES (?, ?, ?, ?, ?);"),
    BANK_UPDATE("UPDATE `customers` SET `name`=?, `brand`=?, `customers`=?, `cards`=?, WHERE `id`=?;"),

    CARD_QUERY("SELECT * FROM `cards` WHERE `owner`=?"),
    CARD_INSERT("INSERT INTO `cards` (`uniqueId`, `owner`, `bank`, `cardBrand`, `cardNumber`, `cardExpires`, `cardHolder`, `cardCVC`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);"),
    CARD_UPDATE("UPDATE `cards` SET `owner`=?, `cardBrand`=?, `cardNumber`=?, `cardExpires`=?, `cardHolder`=?, `cardCVC`=?, WHERE `id`=?;"),

    TABLE_CUSTOMERS("CREATE TABLE IF NOT EXISTS `" + DATABASE.toString() + "`.`customers` (`id` INT NOT NULL AUTO_INCREMENT, `uniqueId` VARCHAR(64) NOT NULL, `name` VARCHAR(64) NOT NULL, `bank` VARCHAR(64) NOT NULL, `cardBrand` VARCHAR(64) NOT NULL, `cardNumber` VARCHAR(64) NOT NULL, `cardExpires` LONG NOT NULL, `cardHolder` VARCHAR(64) NOT NULL, `cardCVC` INT NOT NULL, PRIMARY KEY (`id`, `uniqueId`), UNIQUE INDEX `id_UNIQUE` (`id` ASC)) ENGINE = InnoDB;"),
    TABLE_BANKS("CREATE TABLE IF NOT EXISTS `" + DATABASE.toString() + "`.`banks` (`id` INT NOT NULL AUTO_INCREMENT, `uniqueId` VARCHAR(64) NOT NULL, `name` VARCHAR(64) NOT NULL, `brand` VARCHAR(64) NOT NULL, `customers` INT NOT NULL, `cards` INT NOT NULL, PRIMARY KEY (`id`, `uniqueId`), UNIQUE INDEX `id_UNIQUE` (`id` ASC)) ENGINE = InnoDB;"),
    TABLE_CARDS("CREATE TABLE IF NOT EXISTS `" + DATABASE.toString() + "`.`cards` (`id` INT NOT NULL AUTO_INCREMENT, `uniqueId` VARCHAR(64) NOT NULL, `owner` VARCHAR(64) NOT NULL, `bank` VARCHAR(64) NOT NULL, `cardBrand` VARCHAR(64) NOT NULL, `cardNumber` VARCHAR(64) NOT NULL, `cardExpires` LONG NOT NULL, `cardHolder` VARCHAR(64) NOT NULL, `cardCVC` INT NOT NULL, PRIMARY KEY (`id`, `uniqueId`), UNIQUE INDEX `id_UNIQUE` (`id` ASC)) ENGINE = InnoDB;"),

    LAST_ID("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='" + DATABASE.toString() + "' AND TABLE_NAME=?;");

    private final String query;

    private BankSqlQueries(String query) {
        this.query = query;
    }

    public String toString() {
        return query;
    }
}
