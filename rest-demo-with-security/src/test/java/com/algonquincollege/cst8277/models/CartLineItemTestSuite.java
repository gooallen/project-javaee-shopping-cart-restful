/**********************************************************************egg*m******a******n********************
 * File: CartLineItemTestSuite.java
 * Course materials (19W) CST 8277
 * @author (original) Mike Norman
 *
 * Description: Tests the relationship between {@link com.algonquincollege.cst8277.models.Cart} and {@link com.algonquincollege.cst8277.models.LineItem} </br>
 * 
 * @date (modified) 2019 04 14
 * 
 * @author Byeongyun Goo (#040888224), Zeyang Hu (#040885680), Sohaila Binte Ridwan (#040847430)
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
public class CartLineItemTestSuite implements TestSuiteConstants {

    private static final Class<?> _thisClaz = MethodHandles.lookup().lookupClass();
    private static final Logger logger = LoggerFactory.getLogger(_thisClaz);
    private static final ch.qos.logback.classic.Logger eclipselinkSqlLogger = (ch.qos.logback.classic.Logger) LoggerFactory
            .getLogger(ECLIPSELINK_LOGGING_SQL);

    private static Cart cart_1;
    private static Cart cart_2;
    private static LineItem lineItem_1;
    private static LineItem lineItem_2;
    private static LineItem lineItem_3;
    private static Product product_1;
    private static Product product_2;

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
     * Empties up Cart and LineItem table in DB
     */
    @Before
    public void cleanUpCartAndLineItemTables() {
        em = emf.createEntityManager();
        this.cleanUpLineItemTable();
        this.cleanUpCartTable();
        
        product_1 = new Product();
        product_1.setId(1);
        
        product_2 = new Product();
        product_2.setId(2);
        
        
        cart_1 = new Cart();
        cart_1.setId(1);
        cart_1.setTotal(11.11);
        cart_1.setVersion(1);
        
        cart_2 = new Cart();
        cart_2.setId(2);
        cart_2.setTotal(11.11);
        cart_2.setVersion(2);
        
        lineItem_1 = new LineItem();
        lineItem_1.setId(1);
        lineItem_1.setProduct(product_1);
        lineItem_1.setQuantity(1);
        lineItem_1.setSubtotal(1);
        lineItem_1.setCart(cart_1);
        lineItem_1.setVersion(1);
        
        lineItem_2 = new LineItem();
        lineItem_2.setId(2);
        lineItem_2.setProduct(product_1);
        lineItem_2.setQuantity(2);
        lineItem_2.setSubtotal(2);
        lineItem_2.setCart(cart_2);
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
     * Finds all Carts from database using criteria builder
     * @return List of Cart objects
     */
    public List<Cart> getAllCarts() {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cart> cq = cb.createQuery(Cart.class);
        Root<Cart> rootEntry = cq.from(Cart.class);
        CriteriaQuery<Cart> all = cq.select(rootEntry);
        TypedQuery<Cart> allQuery = em.createQuery(all);
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
     * Persists an Cart object
     * @param cart Cart to persist
     */
    public void saveCart(Cart cart) {
        em.getTransaction().begin();
        em.persist(cart);
        em.getTransaction().commit();
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
     * Adds an Cart and a his LineItem
     * @param cart Cart to persist
     * @param LineItem LineItem to persist
     */
    public void addLineItemAfterAddingRelatedCart(Cart cart, Product product, LineItem lineItem) {
        this.saveCart(cart);
        this.saveProduct(product);
        this.saveLineItem(lineItem);
    }
    
    /**
     * Method to add a LineItem. But it does not respect referential integrity, hence fails.
     * @param LineItem lineItem to persist
     */
    public void addLineItemWithoutAddingRelatedCart(Product product, LineItem lineItem) {
        // try to add LineItem without adding cart
        this.saveProduct(product);
        em.getTransaction().begin();
        em.persist(lineItem);
        em.getTransaction().commit();
    }

    /**
     * Deletes all Cart objects from database
     * @return Number of records deleted
     */
    public int deleteAllCarts() {
        em.getTransaction().begin();
        int deletedCount = em.createQuery("DELETE FROM Cart").executeUpdate();
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
     * Deletes a selected Cart record from database
     * @param cart Cart to delete
     */
    public void deleteAnCart(Cart cart) {
        em.getTransaction().begin();
        em.remove(cart);
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
     * Truncates Cart table
     */
    public void cleanUpCartTable() {
        // make sure that Cart table is empty
        this.deleteAllCarts();
        List<Cart> allCarts = this.getAllCarts();
        assertTrue(allCarts.size() == 0);
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
     * @param cart new Cart
     */
    public void updateLineItemOwner(LineItem lineItem, Cart cart) {
        lineItem.setCart(cart);
        em.getTransaction().begin();
        em.persist(lineItem);
        em.getTransaction().commit();
    }

    /**
     * Helper method to persit latest changes to an Cart property
     * @param cart Cart with updated property
     */
    public void updateCart(Cart cart) {
        em.getTransaction().begin();
        em.merge(cart);
        em.getTransaction().commit();
    }
    
    /**
     * Tests empty tables
     */
    @Test
    public void A_test_empty_tables_at_start() {
        List<Cart> allCarts = this.getAllCarts();
        assertEquals(allCarts.size(), 0);
        
        List<LineItem> allLineItems = this.getAllLineItems();
        assertEquals(allLineItems.size(), 0);
    }

    /**
     * Tests that an Cart can be created without requiring any record in LineItem
     */
    @Test
    public void B_test_create_Cart_success_when_LineItem_table_is_empty() {
        // add cart_1
        this.saveCart(cart_1);
        
        // verify that Cart table has only one record
        List<Cart> allCarts = this.getAllCarts();
        assertEquals(allCarts.size(), 1);
        
        // verify that Cart ID matches
        assertEquals(allCarts.get(0).getId(), cart_1.getId());
    }

    /**
     * Tests that an Cart can exist without a LineItem number
     */
    @Test
    public void C_test_create_Cart_success_without_a_LineItem_number() {
        // add 'cart_1' and cart_1's LineItem number 'lineItem_1'
        try {
            this.addLineItemAfterAddingRelatedCart(cart_1, product_1, lineItem_1);
        } catch (Exception e) {
            fail("Can't add cart_1 and lineItem_1");
        }
        
        // add cart_2 without a LineItem number
        this.saveCart(cart_2);
        
        // verify that Cart table has two records
        List<Cart> allCarts = this.getAllCarts();
        assertEquals(allCarts.size(), 2);
        
        // verify that LineItem table has one record
        List<LineItem> allLineItems = this.getAllLineItems();
        assertEquals(allLineItems.size(), 1);
    }

    /**
     * Tests that a LineItem can be associated with an Cart
     */
    @Test
    public void D_test_create_LineItem_success_for_an_Cart() {
        try {
            this.addLineItemAfterAddingRelatedCart(cart_1, product_1, lineItem_1);
        } catch (Exception e) {
            fail("Can't add lineItem_1");
        }
        
        // verify that Cart table has one record
        List<Cart> allCarts = this.getAllCarts();
        assertEquals(allCarts.size(), 1);
        
        // verify that LineItem table has one record
        List<LineItem> allLineItems = this.getAllLineItems();
        assertEquals(allLineItems.size(), 1);
    }  
    
    /**
     * Tests that a LineItem can not exist without an owner
     */
    @Test(expected = RollbackException.class)
    public void E_test_create_LineItem_failure_for_referential_integrity() {
        // add cart_1
        this.saveCart(cart_1);
        
        // verify that Cart table has at least one record
        List<Cart> allCarts = this.getAllCarts();
        assertTrue(allCarts.size() > 0);
        
        // the following persist will fail, transaction will not complete
        this.addLineItemWithoutAddingRelatedCart(product_1, lineItem_2);
        
        // assert error if there is no exception
        //thrown.reportMissingExceptionWithMessage("No exception thrown");
    }

    /**
     * Tests that an Cart can get rid of their LineItem number
     */
    @Test
    public void F_test_delete_LineItem_success_without_deleting_Cart() {
        // add an Cart and his LineItem number
        try {
            this.addLineItemAfterAddingRelatedCart(cart_1, product_1, lineItem_1);
        } catch (Exception e) {
            fail("Can't add cart_1 or lineItem_1 ");
        }
        
        // remove only LineItem number
        this.deleteALineItem(lineItem_1);
        
        // verify that LineItem table is empty
        List<LineItem> allLineItems = this.getAllLineItems();
        assertEquals(allLineItems.size(), 0);
        
        // verify that Cart table still has a record
        List<Cart> allCarts = this.getAllCarts();
        assertEquals(allCarts.size(), 1);
    }
    

    /**
     * Tests that a LineItem number can be deleted when the owner does not exist 
     */
    @Test
    public void G_test_delete_Cart_success_after_deleting_LineItem() {
        // add an Cart and his LineItem number
        try {
            this.addLineItemAfterAddingRelatedCart(cart_1, product_1, lineItem_1);
        } catch (Exception e) {
            fail("Can't add cart_1 or lineItem_1 ");
        }
        
        // remove the LineItem number first
        this.deleteALineItem(lineItem_1);
        
        // remove the Cart after 
        this.deleteAnCart(cart_1);
        
        // verify that LineItem table is empty
        List<LineItem> allLineItems = this.getAllLineItems();
        assertEquals(allLineItems.size(), 0);
        
        // verify that Cart table is empty
        List<Cart> allCarts = this.getAllCarts();
        assertEquals(allCarts.size(), 0);
    }
    
    /**
     * Tests that a LineItem number can not be deleted if the Cart exists 
     */
    @Test(expected = RollbackException.class)
    public void H_test_delete_Cart_failure_due_to_referential_integrity() {
        // add an Cart and his LineItem number
        try {
            this.addLineItemAfterAddingRelatedCart(cart_1, product_1, lineItem_1);
        } catch (Exception e) {
            fail("Can't add lineItem_1 with valid owner_id");
        }
        
        // try to remove the Cart without removing the LineItem number:
        // this should throw a RollbackException due to foreign key violation
        this.deleteAnCart(cart_1);
        
        // assert error if there is no exception
        //thrown.reportMissingExceptionWithMessage("No exception thrown");
    }
    
    /**
     * Tests that a LineItem number can be moved
     */
    @Test
    public void J_test_update_LineItem_success_a_LineItem_transfer_scenario() {
        try {
            // add an Cart and his LineItem number
            this.addLineItemAfterAddingRelatedCart(cart_1, product_1, lineItem_1);
        } catch (Exception e) {
            fail("Can't add lineItem_1 with valid owner_id");
        }
        
        // add cart_2 without a LineItem number
        this.saveCart(cart_2);
        
        // verify that Cart table has two records
        List<Cart> allCarts = this.getAllCarts();
        assertEquals(allCarts.size(), 2);
        
        // verify that LineItem table has one record
        List<LineItem> allLineItems = this.getAllLineItems();
        assertEquals(allLineItems.size(), 1);
        
        // change the LineItem owner
        this.updateLineItemOwner(lineItem_1, cart_2);
        
        // verify that cart_1 can be deleted without violating integrity constraints
        this.deleteAnCart(cart_1);
        
        // verify that Cart table has one record
        allCarts = this.getAllCarts();
        assertEquals(allCarts.size(), 1);
        
        // verify owner ID
        assertEquals(lineItem_1.getCart().getId(), cart_2.getId());
 
    } 

    /**
     * Tests that a LineItem number needs a valid Cart
     */
    @Test(expected = RollbackException.class)
    public void K_test_update_LineItem_failure_using_non_existing_Cart() {
        try {
            this.addLineItemAfterAddingRelatedCart(cart_1, product_1, lineItem_1);
        } catch (Exception e) {
            fail("Can't add lineItem_1 with valid owner_id");
        }
        
        // verify that Cart table has one record
        List<Cart> allCarts = this.getAllCarts();
        assertEquals(allCarts.size(), 1);
        
        // try to update LineItem owner to an Cart that does not exist in Cart table
        this.updateLineItemOwner(lineItem_1, cart_2);
        
        // assert error if there is no exception after previous operation
        //thrown.reportMissingExceptionWithMessage("No exception thrown");
 
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
