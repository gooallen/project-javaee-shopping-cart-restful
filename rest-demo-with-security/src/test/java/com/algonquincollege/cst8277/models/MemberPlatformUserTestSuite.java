/**********************************************************************egg*m******a******n********************
 * File: PlatformUserPhonesSuite.java
 * Course materials (19W) CST 8277
 * @author (original) Mike Norman
 * Description: Tests the relationship between {@link com.algonquincollege.cst8277.models.PlatformUser} and {@link com.algonquincollege.cst8277.models.Member} </br>
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
public class MemberPlatformUserTestSuite implements TestSuiteConstants {

    private static final Class<?> _thisClaz = MethodHandles.lookup().lookupClass();
    private static final Logger logger = LoggerFactory.getLogger(_thisClaz);

    // test fixture(s)
    public static EntityManagerFactory emf;
    public static EntityManager em;
    public static Server server;

    private static PlatformUser user_1;
    private static PlatformUser user_2;
    private static Member member_1;
    private static Member member_2;

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
    public void cleanUpPlatformUserAndMemberTables() {
        em = emf.createEntityManager();
        this.cleanUpMemberTable();
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

        member_1 = new Member();
        member_1.setId(1);
        member_1.setMemberEmail("mem1@gmail.ca");
        member_1.setMemberFirstName("fname1");
 
        
        member_2 = new Member();
        member_2.setId(2);
        member_2.setMemberEmail("mem2@gmail.ca");
        member_2.setMemberFirstName("fname2");
        
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

    public void saveMember(Member Member) {
        em.getTransaction().begin();
        em.persist(Member);
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

    public List<Member> getAllMembers() {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> cq = cb.createQuery(Member.class);
        Root<Member> rootEntry = cq.from(Member.class);
        CriteriaQuery<Member> all = cq.select(rootEntry);
        TypedQuery<Member> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public void updatePlatformUser(PlatformUser PlatformUser) {
        em.getTransaction().begin();
        em.merge(PlatformUser);
        em.getTransaction().commit();
    }

    public void updateMember(Member Member) {
        em.getTransaction().begin();
        em.merge(Member);
        em.getTransaction().commit();
    }

    public int deleteAllPlatformUsers() {
        em.getTransaction().begin();
        int deletedCount = em.createQuery("DELETE FROM PlatformUser").executeUpdate();
        em.getTransaction().commit();
        return deletedCount;
    }

    public int deleteAllMembers() {
        em.getTransaction().begin();
        int deletedCount = em.createQuery("DELETE FROM Member").executeUpdate();
        em.getTransaction().commit();
        return deletedCount;
    }

    public void cleanUpPlatformUserTable() {
        // make sure that PlatformUser table is empty
        this.deleteAllPlatformUsers();
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertTrue(allPlatformUsers.size() == 0);
    }

    public void cleanUpMemberTable() {
        // make sure that Member table is empty
        this.deleteAllMembers();
        List<Member> allMembers = this.getAllMembers();
        assertTrue(allMembers.size() == 0);
    }

    /**
     * Test that tables are empty at the start
     */
    @Test
    public void A_test_empty_tables_at_start() {
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 0);

        List<Member> allMembers = this.getAllMembers();
        assertEquals(allMembers.size(), 0);
    }

    /**
     * Test that a PlatformUser can have no Member
     */
    @Test
    public void B_create_PlatformUser_sucesss_without_a_Member() {
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

        // verify that Member table is empty
        List<Member> allMembers = this.getAllMembers();
        assertEquals(allMembers.size(), 0);

    }

    /**
     * Test that a PlatformUser can have one Member
     */
    @Test
    public void C_create_PlatformUser_sucesss_with_a_Member() {
        // verify that PlatformUser table is empty
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 0);

        // add user_1
        this.savePlatformUser(user_1);

        // verify that PlatformUser ID matches
        allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.get(0).getId(), user_1.getId());

        // add member_1
        this.saveMember(member_1);

        // assign user_1 with member_1
        user_1.setMember(member_1);
        this.updatePlatformUser(user_1);

        member_1.setPlatformUser(user_1);
        this.updateMember(member_1);
        
        // verify that Member table has only one record
        List<Member> allMembers = this.getAllMembers();
        assertEquals(allMembers.size(), 1);

        // verify that Member is assigned to only one PlatformUser
        assertEquals(member_1.getPlatformUser().getId(), user_1.getId());
    }

    /**
     * Test that a PlatformUser can have multiple Members
     */
    @Test
    public void D_create_PlatformUser_success_PlatformUser_can_be_related_to_maximum_one_Member() {

        // verify that PlatformUser table is empty
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 0);

        // verify that Member table is empty
        List<Member> allMembers = this.getAllMembers();
        assertEquals(allMembers.size(), 0);

        // add user_1
        this.savePlatformUser(user_1);

        // verify that PlatformUser table has only one record
        allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 1);

        // add member_1 and member_2
        this.saveMember(member_1);
        this.saveMember(member_2);

        // assign user_1 with member_1 and member_2
        user_1.setMember(member_1);
        this.updatePlatformUser(user_1);

        user_1.setMember(member_2);
        this.updatePlatformUser(user_1);

        // verify that Member table has two records
        allMembers = this.getAllMembers();
        assertEquals(allMembers.size(), 2);
        
        // verify that user_1 is related to only member_2
        assertEquals(user_1.getMember().getId(), member_2.getId());
        
    }

    /**
     * Test that a Member can have no PlatformUser attached to it
     */
    @Test
    public void E_create_Member_failure_without_a_PlatformUser() {
        // verify that Member table is empty
        List<Member> allMembers = this.getAllMembers();
        assertEquals(allMembers.size(), 0);

        // add member_1
        this.saveMember(member_1);

        // verify that Member table has only one record
        allMembers = this.getAllMembers();
        assertEquals(allMembers.size(), 1);

        // verify that Member ID matches
        assertEquals(allMembers.get(0).getId(), member_1.getId());

        // verify that PlatformUser table is empty
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 0);
    }

    /**
     * Test that a Member can be attached to a single PlatformUser
     */
    @Test
    public void F_create_Member_sucesss_with_a_PlatformUser() {
        // verify that Member table is empty
        List<Member> allMembers = this.getAllMembers();
        assertEquals(allMembers.size(), 0);

        // add member_1
        this.saveMember(member_1);

        // verify that Member id matches
        assertEquals(this.getAllMembers().get(0).getId(), member_1.getId());

        // add user_1
        this.savePlatformUser(user_1);

        // assign user_1 with member_1
        user_1.setMember(member_1);

        this.updatePlatformUser(user_1);

        // verify that Member table has only one record
        allMembers = this.getAllMembers();
        assertEquals(allMembers.size(), 1);

        // verify that Member is assigned to only one PlatformUser
       // assertEquals(allMembers.get(0).getPlatformUsers().size(), 1);

        // verify that Member is assigned to user_1
        //assertEquals(allMembers.get(0).getPlatformUsers().get(0).getId(), user_1.getId());
    }
    /**
     * Test that a PlatformUser can be granted a new Membership
     */
    @Test
    public void G_update_PlatformUser_sucesss_scenario_a_PlatformUser_changes_Membership() {

        // verify that PlatformUser table is empty
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 0);

        // verify that Member table is empty
        List<Member> allMembers = this.getAllMembers();
        assertEquals(allMembers.size(), 0);

        // add member_1 and member_2
        this.saveMember(member_1);
        this.saveMember(member_2);

        // verify that Member table has two records
        allMembers = this.getAllMembers();
        assertEquals(allMembers.size(), 2);

        // initially assign user_1 with member_1
        user_1.setMember(member_1);
        this.savePlatformUser(user_1);

        // verify that PlatformUser table has only one record
        allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 1);

        // assign user_1 new extra Member: member_2
        user_1.setMember(member_2);
        this.updatePlatformUser(user_1);
    }

    /**
     * Test that a Member with some PlatformUsers can be attached to a new PlatformUser
     */
    @Test
    public void H_update_Member_sucesss_scenario_a_Membership_can_be_recycled_and_be_tied_to_new_PlatformUser() {
        // verify that PlatformUser table is empty
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 0);

        // add user_1 and user_2
        this.savePlatformUser(user_1);
        this.savePlatformUser(user_2);

        // verify that Member table has two records
        List<Member> allMembers = this.getAllMembers();
        assertEquals(allMembers.size(), 0);
        allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 2);

        // initially add only user_1 to member_1
        member_1.setPlatformUser(user_1);
        this.saveMember(member_1);

        // verify that Member id matches
        assertEquals(this.getAllMembers().get(0).getId(), member_1.getId());

        // assign user_2 to member_1 as well
        member_1.setPlatformUser(user_2);
        this.updateMember(member_1);

        // verify that all PlatformUsers are assigned to only one Member
        allPlatformUsers = this.getAllPlatformUsers();
    }

    /**
     * Test that all PlatformUsers can be removed from a Member
     */
    @Test
    public void I_delete_PlatformUser_sucesss_scenario() {
        // verify that PlatformUser table is empty
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 0);

        // add user_1
        this.savePlatformUser(user_1);

        // verify that PlatformUser table has one record
        allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 1);
        assertEquals(allPlatformUsers.get(0).getId(), user_1.getId());

        // verify that Member table is empty
        List<Member> allMembers = this.getAllMembers();
        assertEquals(allMembers.size(), 0);

        // add member_1
        this.saveMember(member_1);

        // verify that Member table has one record
        allMembers = this.getAllMembers();
        assertEquals(allMembers.size(), 1);
        assertEquals(this.getAllMembers().get(0).getId(), member_1.getId());
        
        this.deleteAllMembers();
        allMembers = this.getAllMembers();
        assertEquals(allMembers.size(), 0);
        
    }

    /**
     * Test that all Members can be removed even if the PlatformUsers exist
     */
    @Test
    public void J_delete_Member_success_scenario_Membership_can_be_revoked() {
        // verify that PlatformUser table is empty
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 0);

        // verify that Member table is empty
        List<Member> allMembers = this.getAllMembers();
        assertEquals(allMembers.size(), 0);

        // add member_1
        this.saveMember(member_1);

        // verify that Member id matches
        assertEquals(this.getAllMembers().get(0).getId(), member_1.getId());

        // add user_1 and user_2
        this.savePlatformUser(user_1);
        this.savePlatformUser(user_2);

        // verify that PlatformUser table has two records
        allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 2);

        // assign both user_1 and user_2 with member_1
        member_1.setPlatformUser(user_1);
        member_1.setPlatformUser(user_2);
        this.updateMember(member_1);

        // verify that Member table has only one record
        allMembers = this.getAllMembers();
        assertEquals(allMembers.size(), 1);

        // Member has no funding, so quit
        this.deleteAllMembers();

        // verify that Member table is empty
        assertEquals(this.getAllMembers().size(), 0);

        // verify that PlatformUsers still exist within the organization, but are not
        // related to member_1
        allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 2);
    }
    
    /**
     * Test the total number of PlatformUsers for a Member
     */
    @Test
    public void K_read_PlatformUser_sucesss_scenario_count_of_PlatformUsers_for_a_Member() {
        // verify that PlatformUser table is empty
        List<PlatformUser> allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 0);
        
        // add user_1 and user_2
        this.savePlatformUser(user_1);
        this.savePlatformUser(user_2);
        
        // verify that PlatformUser table has two records
        allPlatformUsers = this.getAllPlatformUsers();
        assertEquals(allPlatformUsers.size(), 2);

        // verify that Member table is empty
        List<Member> allMembers = this.getAllMembers();
        assertEquals(allMembers.size(), 0);

        // add member_1 
        this.saveMember(member_1);
        
        // verify that there is only one Member
        assertEquals(this.getAllMembers().size(), 1);

        // assign both user_1 and user_2 to member_1
        member_1.setPlatformUser(user_1);
        member_1.setPlatformUser(user_2);
        this.updateMember(member_1);
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
