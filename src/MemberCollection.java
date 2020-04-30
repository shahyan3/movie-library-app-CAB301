public class MemberCollection {
    private Member member;
    private Member[] memberList;
    private int MAX_MEMBERS = 100;

    public MemberCollection() {
        this.memberList = new Member[MAX_MEMBERS];
        // default root admin user
        Member admin = new Member("john", "doe", "staff", "today123", true);
        this.memberList[0] = admin;
    }

     // Binary search O(log n) time - CONVERT INTO BINARY SEARCH!!! #TODO convert to binary search
    public boolean authenticateMember(String username, String password) {
        for(int i = 0; i < this.memberList.length; i++) {
            if(this.memberList[i].getUsername().equals(username) &&
                    this.memberList[i].getPassword().equals(password)) {
                 return true;
            }
        }
        return false;
    }
    // Binary search O(log n) time - CONVERT INTO BINARY SEARCH!!! #TODO convert to binary search
    public boolean checkMemberExists(String fName, String lName) {
        for(int i = 0; i < this.memberList.length; i++) {
            if(this.memberList[i].getFName().equals(fName) &&
                    this.memberList[i].getLName().equals(lName)) {
                return true;
            }
        }
        System.out.println("False....");
        return false;
    }



//    private boolean addMember(Member member) {
//
//    }
}
