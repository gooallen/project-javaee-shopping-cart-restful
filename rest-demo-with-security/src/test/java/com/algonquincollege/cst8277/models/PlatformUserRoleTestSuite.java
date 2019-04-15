/**********************************************************************egg*m******a******n********************
 * File: PlatformUserPhonesSuite.java
 * Course materials (19W) CST 8277
 * @author (original) Mike Norman
 * Description: Tests the relationship between {@link com.algonquincollege.cst8277.models.PlatformUser} and {@link com.algonquincollege.cst8277.models.PlatformRole} </br>
 * 
 * @date (modified) 2019 04 12
 * 
 * @author Byeongyun Goo (#040888224), Zeyang Hu (#040885680), Sohaila Binte Ridwan (#040847430)
*/

package com.algonquincollege.cst8277.models;

import static com.algonquincollege.cst8277.models.TestSuiteConstants.buildEntityManagerFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlatformUserRoleTestSuite implements TestSuiteConstants {

    private static final Class<?> _thisClaz = MethodHandles.lookup().lookupClass();
    private static final Logger logger = LoggerFactory.getLogger(_thisClaz);

    // test fixture(s)
    public static EntityManagerFactory emf;
    public static EntityManager em;
    public static Server server;

    private static PlatformUser user_1;
    private static PlatformUser user_2;
    private static PlatformRole role_1;
    private static PlatformRole role_2;

    @BeforeClass
    public static void oneTimeSetUp() {
        try {
            logger.debug("oneTimeSetUp");
            server = Server.createTcpServer().start();
            emf = buildEntityManagerFactory(_thisClaz.getSimpleName());
        } catch (Exception e) {
            logger.error("something went wrong building EntityManagerFactory", e);
        }
    }

    @Before
    public void cleanUpPlatformUserAndPlatformRoleTables() {
        em = emf.createEntityManager();
        this.cleanUpPlatformRoleTable();
        this.cleanUpPlatformUserTable();

        user_1 = new PlatformUser();
        user_1.setId(1);
        user_1.setPwHash("1hash");
        user_1.setUsername("1uname");
        user_1.setVersion(1);
        
        user_2 = new PlatformUser();
        user_2.setId(2);
        user_2.setPwHash("2hash");
        user_2.setUsername("2uname");
        user_2.setVersion(1);

        role_1 = new PlatformRole();
        role_1.setId(1);
        role_1.setRoleName("1rname");
        role_1.setVersion(1);
        
        role_2 = new PlatformRole();
        role_2.setId(2);
        role_2.setRoleName("2rname");
        role_2.setVersion(1);
        
    }

    @After
    public void closeEntityManager() {
        em.close();
    }

    public void savePlatformUser(PlatformUser emp) {
        em.getTransaction().begin();
        em.persist(emp);
        em.getTransaction().commit();
    }

    public void savePlatformRole(PlatformRole PlatformRole) {
        em.getTransaction().begin();
        em.persist(PlatformRole);
        em.getTransaction().commit();
    }

    public List<PlatformUser> getAllPlatformUsers() {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PlatformUser> cq = cb.createQuery(PlatformUser.class);
        Root<PlatformUser> rootEntry = cq.from(PlatformUser.class);
        CriteriaQuery<PlatformUser> all = cq.select(rootEntry);
        TypedQuery<PlatformUser> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public List<PlatformRole> getAllPlatformRoles() {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PlatformRole> cq = cb.createQuery(PlatformRole.class);
        Root<PlatformRole> rootEntry = cq.from(PlatformRole.class);
        CriteriaQuery<PlatformRole> all = cq.select(rootEntry);
        TypedQuery<PlatformRole> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public void updatePlatformUser(PlatformUser PlatformUser) {
        em.getTransaction().begin();
        em.merge(PlatformUser);
        em.getTransaction().commit();
    }

    public void updatePlatformRole(PlatformRole PlatformRole) {
        em.getTransaction().begin();
        em.merge(PlatformRole);
        em.getTransaction().commit();
    }

    public int deleteAllPlatformUsers() {
        em.getTransaction().begin();
        int deletedCount = em.createQuery("DELETE FROM PlatformUser").executeUpdate();
        em.getTransaction().commit();
        return deletedCount;
    }

    public int deleteAllPlatformRoles() {
        em.getTransaction().begin();
        int deletedCount = em.createQuery("DELETE FROM PlatformRole").executeUpdate();
        em.getTransaction().commit();
        return deletedCount;
    }

    public void cleanUpPlatformUserTable() {
        // make sure that PlatformUser table is empty
        this.deleteAllPlatformUsers();
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertTrue(allPlatformUsers.size() == 0);
    }

    public void cleanUpPlatformRoleTable() {
        // make sure that PlatformRole table is empty
        this.deleteAllPlatformRoles();
        List<PlatformRole> allPlatformRoles = this.getAllPlatformRoles();
        assertTrue(allPlatformRoles.size() == 0);
    }

    /**
     * Test that tables are empty at the start
     */
    @Test
    public void A_test_empty_tables_at_start() {
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 0);

        List<PlatformRole> allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 0);
    }

    /**
     * Test that an PlatformUser can have no PlatformRole
     */
    @Test
    public void B_create_PlatformUser_sucesss_without_a_PlatformRole() {
        // verify that PlatformUser table is empty
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 0);

        // add user_1
        this.savePlatformUser(user_1);

        // verify that PlatformUser table has only one record
        allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 1);

        // verify that PlatformUser ID matches
        assertEquals(allPlatformUsers.get(0).getId(), user_1.getId());

        // verify that PlatformRole table is empty
        List<PlatformRole> allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 0);

    }

    /**
     * Test that an PlatformUser can have one PlatformRole
     */
    @Test
    public void C_create_PlatformUser_sucesss_with_a_PlatformRole() {
        // verify that PlatformUser table is empty
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 0);

        // add user_1
        this.savePlatformUser(user_1);

        // verify that PlatformUser ID matches
        allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.get(0).getId(), user_1.getId());

        // add role_1
        this.savePlatformRole(role_1);

        // assign user_1 with role_1
        user_1.addPlatformRole(role_1);

        this.updatePlatformUser(user_1);

        // verify that PlatformRole table has only one record
        List<PlatformRole> allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 1);

        // verify that PlatformRole is assigned to only one PlatformUser
        assertEquals(allPlatformRoles.get(0).getPlatformUsers().size(), 1);

        // verify that PlatformRole is assigned to user_1
        assertEquals(allPlatformRoles.get(0).getPlatformUsers().get(0).getId(), user_1.getId());
    }

    /**
     * Test that an PlatformUser can have multiple PlatformRoles
     */
    @Test
    public void D_create_PlatformUser_success_with_multiple_PlatformRoles() {

        // verify that PlatformUser table is empty
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 0);

        // verify that PlatformRole table is empty
        List<PlatformRole> allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 0);

        // add user_1
        this.savePlatformUser(user_1);

        // verify that PlatformUser table has only one record
        allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 1);

        // add role_1 and role_2
        this.savePlatformRole(role_1);
        this.savePlatformRole(role_2);

        // assign user_1 with role_1 and role_2
        user_1.addPlatformRole(role_1);
        user_1.addPlatformRole(role_2);

        this.updatePlatformUser(user_1);

        // verify that PlatformRole table has two records
        allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 2);
        
        // verify that all PlatformRoles are assigned to only user_1
        PlatformUser reverseLookup_user_1 = allPlatformRoles.get(0).getPlatformUsers().get(0);
        assertEquals(reverseLookup_user_1.getId(), user_1.getId());
        PlatformUser reverseLookup_user_2 = allPlatformRoles.get(0).getPlatformUsers().get(0);
        assertEquals(reverseLookup_user_2.getId(), user_1.getId());
    }

    /**
     * Test that a PlatformRole can have no PlatformUser working for it
     */
    @Test
    public void E_create_PlatformRole_sucesss_without_a_PlatformUser() {
        // verify that PlatformRole table is empty
        List<PlatformRole> allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 0);

        // add role_1
        this.savePlatformRole(role_1);

        // verify that PlatformRole table has only one record
        allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 1);

        // verify that PlatformRole ID matches
        assertEquals(allPlatformRoles.get(0).getId(), role_1.getId());

        // verify that PlatformUser table is empty
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 0);
    }

    /**
     * Test that a PlatformRole can have a single PlatformUser
     */
    @Test
    public void F_create_PlatformRole_sucesss_with_a_PlatformUser() {
        // verify that PlatformRole table is empty
        List<PlatformRole> allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 0);

        // add role_1
        this.savePlatformRole(role_1);

        // verify that PlatformRole id matches
        assertEquals(this.getAllPlatformRoles().get(0).getId(), role_1.getId());

        // add user_1
        this.savePlatformUser(user_1);

        // assign user_1 with role_1
        user_1.addPlatformRole(role_1);

        this.updatePlatformUser(user_1);

        // verify that PlatformRole table has only one record
        allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 1);

        // verify that PlatformRole is assigned to only one PlatformUser
        assertEquals(allPlatformRoles.get(0).getPlatformUsers().size(), 1);

        // verify that PlatformRole is assigned to user_1
        assertEquals(allPlatformRoles.get(0).getPlatformUsers().get(0).getId(), user_1.getId());
    }

    /**
     * Test that a PlatformRole can have multiple PlatformUsers working for it
     */
    @Test
    public void G_create_PlatformRole_sucesss_with_multiple_PlatformUsers() {
        // verify that PlatformRole table is empty
        List<PlatformRole> allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 0);

        // add role_1
        this.savePlatformRole(role_1);

        // verify that PlatformRole id matches
        assertEquals(this.getAllPlatformRoles().get(0).getId(), role_1.getId());

        // add user_1 and user_2
        this.savePlatformUser(user_1);
        this.savePlatformUser(user_2);

        // assign role_1 to both user_1 and user_2
        user_1.addPlatformRole(role_1);
        user_2.addPlatformRole(role_1);

        this.updatePlatformUser(user_1);
        this.updatePlatformUser(user_2);

        // verify that PlatformRole table has only one record
        allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 1);

        // verify that all PlatformUsers are assigned to only one PlatformRole
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 2);
        assertEquals(allPlatformUsers.get(0).getPlatformRoles().size(), 1);
        assertEquals(allPlatformUsers.get(1).getPlatformRoles().size(), 1);

        // verify that all PlatformUsers are assigned to only role_1
        PlatformRole reverseLookup_role_1 = allPlatformUsers.get(0).getPlatformRoles().iterator().next();
        assertEquals(reverseLookup_role_1.getId(), role_1.getId());
        PlatformRole reverseLookup_role_2 = allPlatformUsers.get(1).getPlatformRoles().iterator().next();
        assertEquals(reverseLookup_role_2.getId(), role_1.getId());
    }

    /**
     * Test that an PlatformUser can take on new PlatformRoles on top of the existing
     * assignment
     */
    @Test
    public void H_update_PlatformUser_sucesss_scenario_a_PlatformUser_gets_new_PlatformRole() {

        // verify that PlatformUser table is empty
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 0);

        // verify that PlatformRole table is empty
        List<PlatformRole> allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 0);

        // add role_1 and role_2
        this.savePlatformRole(role_1);
        this.savePlatformRole(role_2);

        // verify that PlatformRole table has two records
        allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 2);

        // initially assign user_1 with role_1
        user_1.addPlatformRole(role_1);
        this.savePlatformUser(user_1);

        // verify that PlatformUser table has only one record
        allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 1);

        // assign user_1 new extra PlatformRole: role_2
        user_1.addPlatformRole(role_2);
        this.updatePlatformUser(user_1);

        // verify that all PlatformRoles are assigned to only one PlatformUser
        assertEquals(allPlatformRoles.get(0).getPlatformUsers().size(), 1);
        assertEquals(allPlatformRoles.get(1).getPlatformUsers().size(), 1);

        // verify that all PlatformRoles are assigned to only user_1
        PlatformUser reverseLookup_user_1 = allPlatformRoles.get(0).getPlatformUsers().get(0);
        assertEquals(reverseLookup_user_1.getId(), user_1.getId());
        PlatformUser reverseLookup_user_2 = allPlatformRoles.get(1).getPlatformUsers().get(0);
        assertEquals(reverseLookup_user_2.getId(), user_1.getId());
    }

    /**
     * Test that a PlatformRole can hire extra PlatformUser
     */
    @Test
    public void I_update_PlatformRole_sucesss_scenario_a_PlatformRole_ties_to_new_PlatformUser() {
        // verify that PlatformUser table is empty
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 0);

        // add user_1 and user_2
        this.savePlatformUser(user_1);
        this.savePlatformUser(user_2);

        // verify that PlatformRole table has two records
        List<PlatformRole> allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 0);
        allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 2);

        // initially add only user_1 to role_1
        role_1.addPlatformUser(user_1);
        this.savePlatformRole(role_1);

        // verify that PlatformRole id matches
        assertEquals(this.getAllPlatformRoles().get(0).getId(), role_1.getId());

        // verify that role_1 has only one PlatformUser
        assertEquals(this.getAllPlatformRoles().get(0).getPlatformUsers().size(), 1);

        // verify that role_1 has only user_1 as the PlatformUser
        assertEquals(this.getAllPlatformRoles().get(0).getPlatformUsers().get(0).getId(), user_1.getId());

        // assign user_2 to role_1 as well
        role_1.addPlatformUser(user_2);
        this.updatePlatformRole(role_1);

        // verify that role_1 now has two PlatformUsers
        assertEquals(this.getAllPlatformRoles().get(0).getPlatformUsers().size(), 2);

        // verify that all PlatformUsers are assigned to only one PlatformRole
        allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.get(0).getPlatformRoles().size(), 1);
        assertEquals(allPlatformUsers.get(1).getPlatformRoles().size(), 1);

        // verify that all PlatformUsers are assigned to only role_1
        PlatformRole reverseLookup_role_1 = allPlatformUsers.get(0).getPlatformRoles().iterator().next();
        assertEquals(reverseLookup_role_1.getId(), role_1.getId());
        PlatformRole reverseLookup_role_2 = allPlatformUsers.get(1).getPlatformRoles().iterator().next();
        assertEquals(reverseLookup_role_2.getId(), role_1.getId());
    }

    /**
     * Test that all PlatformUsers can be removed from a PlatformRole
     */
    @Test
    public void J_delete_PlatformUser_sucesss_scenario_PlatformRole_is_without_any_PlatformUser() {
        // verify that PlatformUser table is empty
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 0);

        // add user_1
        this.savePlatformUser(user_1);

        // verify that PlatformUser table has one record
        allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 1);
        assertEquals(allPlatformUsers.get(0).getId(), user_1.getId());

        // verify that PlatformRole table is empty
        List<PlatformRole> allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 0);

        // add role_1
        this.savePlatformRole(role_1);

        // verify that PlatformRole table has one record
        allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 1);
        assertEquals(this.getAllPlatformRoles().get(0).getId(), role_1.getId());

        // assign user_1 to role_1
        role_1.addPlatformUser(user_1);
        this.updatePlatformRole(role_1);

        // verify that PlatformRole has only one PlatformUser
        allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 1);
        assertEquals(allPlatformRoles.get(0).getPlatformUsers().size(), 1);
        assertEquals(allPlatformRoles.get(0).getPlatformUsers().get(0).getId(), user_1.getId());

        // verify that PlatformRole role_1 exists
        allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 1);
        assertEquals(this.getAllPlatformRoles().get(0).getId(), role_1.getId());
    }

    /**
     * Test that all PlatformRoles can be removed even if the PlatformUsers exist
     */
    @Test
    public void K_delete_PlatformRole_success_scenario_PlatformRole_removed() {
        // verify that PlatformUser table is empty
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 0);

        // verify that PlatformRole table is empty
        List<PlatformRole> allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 0);

        // add role_1
        this.savePlatformRole(role_1);

        // verify that PlatformRole id matches
        assertEquals(this.getAllPlatformRoles().get(0).getId(), role_1.getId());

        // add user_1 and user_2
        this.savePlatformUser(user_1);
        this.savePlatformUser(user_2);

        // verify that PlatformUser table has two records
        allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 2);

        // assign both user_1 and user_2 with role_1
        role_1.addPlatformUser(user_1);
        role_1.addPlatformUser(user_2);
        this.updatePlatformRole(role_1);

        // verify that PlatformRole table has only one record
        allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 1);

        // verify that PlatformRole has two PlatformUsers
        assertEquals(allPlatformRoles.get(0).getPlatformUsers().size(), 2);

        // PlatformRole has no funding, so quit
        this.deleteAllPlatformRoles();

        // verify that PlatformRole table is empty
        assertEquals(this.getAllPlatformRoles().size(), 0);

        // verify that PlatformUsers still exist within the organization, but are not
        // related to role_1
        allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 2);
    }
    
    /**
     * Test the total number of PlatformRoles for an PlatformUser
     */
    @Test
    public void L_read_PlatformRole_sucesss_scenario_count_of_PlatformRoles_for_a_PlatformUser() {
        // verify that PlatformUser table is empty
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 0);
        
        // add user_1 
        this.savePlatformUser(user_1);
        
        // verify that PlatformUser table has one record
        allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 1);

        // verify that PlatformRole table is empty
        List<PlatformRole> allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 0);

        // add role_1 and role_2
        this.savePlatformRole(role_1);
        this.savePlatformRole(role_2);

        // verify that there are two PlatformRoles
        assertEquals(this.getAllPlatformRoles().size(), 2);

        // assign both role_1 and role_2 to user_1
        role_1.addPlatformUser(user_1);
        role_2.addPlatformUser(user_1);
        this.updatePlatformRole(role_1);
        this.updatePlatformRole(role_2);
        
        // re-read PlatformRoles from DB
        allPlatformRoles = this.getAllPlatformRoles();

        // verify that both PlatformRoles have only one PlatformUser
        assertEquals(allPlatformRoles.get(0).getPlatformUsers().size(), 1);
        assertEquals(allPlatformRoles.get(1).getPlatformUsers().size(), 1);
        
        // get the count for user_1's PlatformRoles
        int user_1_proj = 0;
        for (PlatformRole PlatformRole: allPlatformRoles) {
            for (PlatformUser PlatformUser: PlatformRole.getPlatformUsers()) {
                if ( PlatformUser.getId() == user_1.getId() ) {
                    user_1_proj++;
                }
            }
        }
        
        // verify that user_1 has two PlatformRoles
        assertEquals(user_1_proj, 2);
    }

    /**
     * Test the total number of PlatformUsers for a PlatformRole
     */
    @Test
    public void M_read_PlatformUser_sucesss_scenario_count_of_PlatformUsers_for_a_PlatformRole() {
        // verify that PlatformUser table is empty
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 0);
        
        // add user_1 and user_2
        this.savePlatformUser(user_1);
        this.savePlatformUser(user_2);
        
        // verify that PlatformUser table has two records
        allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 2);

        // verify that PlatformRole table is empty
        List<PlatformRole> allPlatformRoles = this.getAllPlatformRoles();
        assertEquals(allPlatformRoles.size(), 0);

        // add role_1 
        this.savePlatformRole(role_1);
        
        // verify that there is only one PlatformRole
        assertEquals(this.getAllPlatformRoles().size(), 1);

        // assign both user_1 and user_2 to role_1
        role_1.addPlatformUser(user_1);
        role_1.addPlatformUser(user_2);
        this.updatePlatformRole(role_1);
        
        // re-read PlatformRoles from DB
        allPlatformRoles = this.getAllPlatformRoles();

        // verify that role_1 has two PlatformUsers
        assertEquals(allPlatformRoles.get(0).getPlatformUsers().size(), 2);
    }

    /**
     * One time tear down after executing the test suite
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
