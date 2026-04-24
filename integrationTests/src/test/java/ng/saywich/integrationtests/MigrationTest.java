package ng.saywich.integrationtests;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import ng.saywich.storageservice.infrastructure.persistence.jparepositories.cars.ICarModelDaoRepository;
import ng.saywich.storageservice.infrastructure.persistence.jparepositories.parts.*;
import ng.saywich.storageservice.infrastructure.persistence.models.cars.CarModelDao;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.EngineDao;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.InteriorDao;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.RimsDao;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.TransmissionDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Testcontainers
@ActiveProfiles("test")
@SpringBootTest(classes = TestApplication.class)
public class MigrationTest {
    @Autowired
    private ICarModelDaoRepository carModelDaoRepository;

    @Autowired
    private IEngineDaoRepository enginesDaoRepository;

    @Autowired
    private IInteriorDaoRepository interiorDaoRepository;

    @Autowired
    private IRimsDaoRepository rimsDaoRepository;

    @Autowired
    private ITransmissionDaoRepository transmissionDaoRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
        new PostgreSQLContainer<>("postgres")
            .withDatabaseName("automotive")
            .withUsername("admin")
            .withPassword("admin");

    @Test
    public void migrationTest() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                postgres.getJdbcUrl(),
                postgres.getUsername(),
                postgres.getPassword()
            );

            Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(connection));

            Liquibase liquibase = new Liquibase(
                "db/changelog/changelog.yaml",
                new ClassLoaderResourceAccessor(),
                database
            );

            liquibase.update(new Contexts(), new LabelExpression());
        } catch (SQLException | LiquibaseException e) {
            throw new RuntimeException(e);
        }

        List<CarModelDao> carModeldaoList = carModelDaoRepository.findAll();
        List<EngineDao> enginedaoList = enginesDaoRepository.findAll();
        List<InteriorDao> interiorDaoList = interiorDaoRepository.findAll();
        List<RimsDao> rimsDaoList = rimsDaoRepository.findAll();
        List<TransmissionDao> transmissionDaoList = transmissionDaoRepository.findAll();

        Assertions.assertEquals(2, carModeldaoList.size());
        Assertions.assertEquals(2, enginedaoList.size());
        Assertions.assertEquals(2, interiorDaoList.size());
        Assertions.assertEquals(2, rimsDaoList.size());
        Assertions.assertEquals(2, transmissionDaoList.size());
    }
}
