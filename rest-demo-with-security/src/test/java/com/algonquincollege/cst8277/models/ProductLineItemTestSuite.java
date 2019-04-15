/**********************************************************************egg*m******a******n********************
 * File: ProductLineItemTestSuite.java
 * Course materials (19W) CST 8277
 * @author (original) Mike Norman
 *
 * Description: Tests the relationship between {@link com.algonquincollege.cst8277.models.Product} and {@link com.algonquincollege.cst8277.models.LineItem} </br>
 * 
 * @date (modified) 2019 04 14
 * 
 * @author Hu, Byeongyun, and Sohaila Binte Ridwan (040847430) 
 *
 */
package com.algonquincollege.cst8277.models;

import static com.algonquincollege.cst8277.models.TestSuiteConstants.buildEntityManagerFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.h2.tools.Server;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductLineItemTestSuite implements TestSuiteConstants {

    private static final Class<?> _thisClaz = MethodHandles.lookup().lookupClass();
    private static final Logger logger = LoggerFactory.getLogger(_thisClaz);
    private static final ch.qos.logback.classic.Logger eclipselinkSqlLogger = (ch.qos.logback.classic.Logger) LoggerFactory
            .getLogger(ECLIPSELINK_LOGGING_SQL);

    private static LineItem lineItem_1;
    private static LineItem lineItem_2;
    private static LineItem lineItem_3;
    private static Product product_1;
    private static Product product_2;
    private static Product product_3;

    // test fixture(s)
    public static EntityManagerFactory emf;
    public static EntityManager em;
    public static Server server;
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    /**
     * Setup one-time, before starting the test suite
     */
    @BeforeClass
    public static void oneTimeSetUp() {
        try {
            logger.debug("oneTimeSetUp");
            // create in-process H2 server so we can 'see' into database
            // use "jdbc:h2:tcp://localhost:9092/mem:assignment3-testing" in Db Perspective
            // (connection in .dbeaver-data-sources.xml so should be immediately useable
            server = Server.createTcpServer().start();
            emf = buildEntityManagerFactory(_thisClaz.getSimpleName());
            
        } catch (Exception e) {
            logger.error("something went wrong building EntityManagerFactory", e);
        }
    }
    
    /**
     * Method that runs before each test case. 
     * Sets up some test objects.
     * Empties up Product and LineItem table in DB
     */
    @Before 
    public void cleanUpProductAndLineItemTables() {
        em = emf.createEntityManager();
        this.cleanUpLineItemTable();
        this.cleanUpProductTable();
        
        product_1 = new Product();
        product_1.setId(1);
        product_1.setVersion(1);
        
        product_2 = new Product();
        product_2.setId(2);
        product_2.setVersion(1);
        
        product_3 = new Product();
        product_3.setId(2);
        product_3.setVersion(1);

        
        lineItem_1 = new LineItem();
        lineItem_1.setId(1);
        lineItem_1.setProduct(product_1);
        lineItem_1.setQuantity(1);
        lineItem_1.setSubtotal(1);
        lineItem_1.setProduct(product_1);
        lineItem_1.setVersion(1);
        
        lineItem_2 = new LineItem();
        lineItem_2.setId(2);
        lineItem_2.setProduct(product_1);
        lineItem_2.setQuantity(2);
        lineItem_2.setSubtotal(2);
        lineItem_2.setProduct(product_2);
        lineItem_2.setVersion(2);
        
        lineItem_3 = new LineItem();
        lineItem_3.setId(3);
        lineItem_3.setProduct(new Product());
        lineItem_3.setQuantity(3);
        lineItem_3.setSubtotal(3);
        lineItem_3.setVersion(3);
    }
    
    /**
     * Runs after each test case.
     */
    @After
    public void closeEntityManager() {
        em.close();
    }
    
    /**
     * Finds all Products from database using criteria builder
     * @return List of Product objects
     */
    public List<Product> getAllProducts() {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> rootEntry = cq.from(Product.class);
        CriteriaQuery<Product> all = cq.select(rootEntry);
        TypedQuery<Product> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
    
    /**
     * Finds all LineItem numbers from database using criteria builder
     * @return List of LineItem objects
     */
    public List<LineItem> getAllLineItems() {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LineItem> cq = cb.createQuery(LineItem.class);
        Root<LineItem> rootEntry = cq.from(LineItem.class);
        CriteriaQuery<LineItem> all = cq.select(rootEntry);
        TypedQuery<LineItem> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
    
    
    /**
     * Persists a LineItem object
     * @param lineItem LineItem to persist
     */
    public void saveLineItem(LineItem lineItem) {
        em.getTransaction().begin();
        em.persist(lineItem);
        em.getTransaction().commit();
    }
    
    /**
     * Persists a Product object
     * @param product LineItem to persist
     */
    public void saveProduct(Product product) {
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }
    
    /**
     * Adds an Product and a his LineItem
     * @param LineItem LineItem to persist
     */
    public void addLineItemAfterAddingRelatedProduct(Product product, LineItem lineItem) {
        this.saveProduct(product);
        this.saveLineItem(lineItem);
    }
    
    /**
     * Method to add a LineItem. But it does not respect referential integrity, hence fails.
     * @param LineItem lineItem to persist
     */
    public void addLineItemWithoutAddingRelatedProduct(Product product, LineItem lineItem) {
        // try to add LineItem without adding Product
        this.saveProduct(product);
        em.getTransaction().begin();
        em.persist(lineItem);
        em.getTransaction().commit();
    }

    /**
     * Deletes all Product objects from database
     * @return Number of records deleted
     */
    public int deleteAllProducts() {
        em.getTransaction().begin();
        int deletedCount = em.createQuery("DELETE FROM Product").executeUpdate();
        em.getTransaction().commit();
        return deletedCount;
    }

    /**
     * Deletes all LineItem objects from database
     * @return Number of records deleted
     */
    public int deleteAllLineItemNumberss() {
        em.getTransaction().begin();
        int deletedCount = em.createQuery("DELETE FROM LineItem").executeUpdate();
        em.getTransaction().commit();
        return deletedCount;
    }
    
    /**
     * Deletes a selected Product record from database
     * @param Product Product to delete
     */
    public void deleteAnProduct(Product Product) {
        em.getTransaction().begin();
        em.remove(Product);
        em.getTransaction().commit();
    }

    /**
     * Deletes a selected LineItem record from database
     * @param LineItem LineItem to delete
     */
    public void deleteALineItem(LineItem LineItem) {
        em.getTransaction().begin();
        em.remove(LineItem);
        em.getTransaction().commit();
    }
    
    /**
     * Truncates Product table
     */
    public void cleanUpProductTable() {
        // make sure that Product table is empty
        this.deleteAllProducts();
        List<Product> allProducts = this.getAllProducts();
        assertTrue(allProducts.size() == 0);
    }
    

    
    /**
     * Truncates LineItem table
     */
    public void cleanUpLineItemTable() {
        // make sure that LineItem table is empty
        this.deleteAllLineItemNumberss();
        List<LineItem> allLineItems = this.getAllLineItems();
        assertTrue(allLineItems.size() == 0);
    }
    
    /**
     * Helper method to update owner of a LineItem
     * @param LineItem LineItem object
     * @param Product new Product
     */
    public void updateLineItem(LineItem lineItem) {
        em.getTransaction().begin();
        em.persist(lineItem);
        em.getTransaction().commit();
    }

    /**
     * Helper method to persit latest changes to an Product property
     * @param Product Product with updated property
     */
    public void updateProduct(Product Product) {
        em.getTransaction().begin();
        em.merge(Product);
        em.getTransaction().commit();
    }
    
    /**
     * Tests empty tables
     */
    @Test
    public void A_test_empty_tables_at_start() {
        List<Product> allProducts = this.getAllProducts();
        assertEquals(allProducts.size(), 0);
        
        List<LineItem> allLineItems = this.getAllLineItems();
        assertEquals(allLineItems.size(), 0);
    }

    /**
     * Tests that an Product can be created without requiring any record in LineItem
     */
    @Test
    public void B_test_create_Product_success_when_LineItem_table_is_empty() {
        // add Product_1
        this.saveProduct(product_1);
        
        // verify that Product table has only one record
        List<Product> allProducts = this.getAllProducts();
        assertEquals(allProducts.size(), 1);
        
        // verify that Product ID matches
        assertEquals(allProducts.get(0).getId(), product_1.getId());
    }

    /**
     * Tests that an Product can exist without a LineItem number
     */
    @Test
    public void C_test_create_Product_success_without_a_LineItem() {
        // add 'Product_1' and Product_1's LineItem number 'lineItem_1'
        try {
            this.addLineItemAfterAddingRelatedProduct(product_1, lineItem_1);
        } catch (Exception e) {
            fail("Can't add Product_1 and lineItem_1");
        }
        
        // add Product_2 without a LineItem number
        this.saveProduct(product_2);
        
        // verify that Product table has two records
        List<Product> allProducts = this.getAllProducts();
        assertEquals(allProducts.size(), 2);
        
        // verify that LineItem table has one record
        List<LineItem> allLineItems = this.getAllLineItems();
        assertEquals(allLineItems.size(), 1);
    }

    /**
     * Tests that a LineItem can be associated with an Product
     */
    @Test
    public void D_test_create_LineItem_success_for_an_Product() {
        try {
            this.addLineItemAfterAddingRelatedProduct(product_1, lineItem_1);
        } catch (Exception e) {
            fail("Can't add lineItem_1");
        }
        
        // verify that Product table has one record
        List<Product> allProducts = this.getAllProducts();
        assertEquals(allProducts.size(), 1);
        
        // verify that LineItem table has one record
        List<LineItem> allLineItems = this.getAllLineItems();
        assertEquals(allLineItems.size(), 1);
    }  
    
    /**
     * Tests that a LineItem can not exist without an owner
     */
    @Test(expected = RollbackException.class)
    public void E_test_create_LineItem_failure_for_referential_integrity() {
        // add Product_1
        this.saveProduct(product_1);
        
        // verify that Product table has at least one record
        List<Product> allProducts = this.getAllProducts();
        assertTrue(allProducts.size() > 0);
        
        // the following persist will fail, transaction will not complete
        this.addLineItemWithoutAddingRelatedProduct(product_1, lineItem_2);
        
        // assert error if there is no exception
        //thrown.reportMissingExceptionWithMessage("No exception thrown");
    }

    /**
     * Tests that an Product can get rid of their LineItem number
     */
    @Test
    public void F_test_delete_LineItem_success_without_deleting_Product() {
        // add an Product and his LineItem number
        try {
            this.addLineItemAfterAddingRelatedProduct(product_1, lineItem_1);
        } catch (Exception e) {
            fail("Can't add Product_1 or lineItem_1 ");
        }
        
        // remove only LineItem number
        this.deleteALineItem(lineItem_1);
        
        // verify that LineItem table is empty
        List<LineItem> allLineItems = this.getAllLineItems();
        assertEquals(allLineItems.size(), 0);
        
        // verify that Product table still has a record
        List<Product> allProducts = this.getAllProducts();
        assertEquals(allProducts.size(), 1);
    }
    

    /**
     * Tests that a LineItem number can be deleted when the owner does not exist 
     */
    @Test
    public void G_test_delete_Product_success_after_deleting_LineItem() {
        // add an Product and his LineItem number
        try {
            this.addLineItemAfterAddingRelatedProduct(product_1, lineItem_1);
        } catch (Exception e) {
            fail("Can't add Product_1 or lineItem_1 ");
        }
        
        // remove the LineItem number first
        this.deleteALineItem(lineItem_1);
        
        // remove the Product after 
        this.deleteAnProduct(product_1);
        
        // verify that LineItem table is empty
        List<LineItem> allLineItems = this.getAllLineItems();
        assertEquals(allLineItems.size(), 0);
        
        // verify that Product table is empty
        List<Product> allProducts = this.getAllProducts();
        assertEquals(allProducts.size(), 0);
    }
    
    /**
     * Tests that a LineItem number can be moved
     */
    @Test
    public void H_test_update_LineItem_success_change_quantity_scenario() {
        try {
            // add an Product and his LineItem number
            this.addLineItemAfterAddingRelatedProduct(product_1, lineItem_1);
        } catch (Exception e) {
            fail("Can't add lineItem_1 with valid owner_id");
        }
        
        // add Product_2 without a LineItem number
        this.saveProduct(product_2);
        
        // verify that Product table has two records
        List<Product> allProducts = this.getAllProducts();
        assertEquals(allProducts.size(), 2);
        
        // verify that LineItem table has one record
        List<LineItem> allLineItems = this.getAllLineItems();
        assertEquals(allLineItems.size(), 1);
        
        lineItem_1.setQuantity(100);
        // change the LineItem quantity
        this.updateLineItem(lineItem_1);
        
        // verify that LineItem table has one record
        allLineItems = this.getAllLineItems();
        assertEquals(allLineItems.size(), 1);
        assertEquals(allLineItems.get(0).getQuantity(), 100);
        
    } 

    @Test
    public void I_test_read_LineItem_and_Product_success_scenario() {
        try {
            // add an Product and his LineItem number
            this.addLineItemAfterAddingRelatedProduct(product_1, lineItem_1);
        } catch (Exception e) {
            fail("Can't add lineItem_1 with valid owner_id");
        }
        
        // verify that Product table has two records
        List<Product> allProducts = this.getAllProducts();
        assertEquals(allProducts.size(), 1);
        
        // verify that LineItem table has one record
        List<LineItem> allLineItems = this.getAllLineItems();
        assertEquals(allLineItems.size(), 1);
      
    } 
    
   
    /**
     * One-time tear down after test suite is run
     */
    @AfterClass
    public static void oneTimeTearDown() {
        logger.debug("oneTimeTearDown");
        if (emf != null) {
            emf.close();
        }
        if (server != null) {
            server.stop();
        }
    }

}
