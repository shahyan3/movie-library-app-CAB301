public class MemberCollection {
//    private Member member;
    private Member[] memberList;
    private int MAX_MEMBERS = 100;

    private static int currentMemberCountIndex;

    public MemberCollection() {
        this.memberList = new Member[MAX_MEMBERS];
        // default root admin user
        Member admin = new Member("john", "doe", "staff", "today123", "0423911239", true);
        this.memberList[0] = admin;
        currentMemberCountIndex = 0;
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
    public boolean checkMemberExists(String firstName, String lastName) {
         for(int i = 0; i < this.memberList.length; i++) { // first check if object exists otherwise java throws null exception
            if(this.memberList[i] != null && this.memberList[i].getFName().equals(firstName) &&
                    this.memberList[i].getLName().equals(lastName)) {
                return true;
            }
        }
         return false;
    }

    // Binary search O(log n) time - CONVERT INTO BINARY SEARCH!!! #TODO convert to binary search
    public Member getMember(String firstName, String lastName) {
        for(int i = 0; i < this.memberList.length; i++) { // first check if object exists otherwise java throws null exception
            if(this.memberList[i] != null && this.memberList[i].getFName().equals(firstName) &&
                    this.memberList[i].getLName().equals(lastName)) {

                return this.memberList[i];
            }
        }
        return null;
    }

    // register non-admin users
    public void registerUser(String fName, String lName, String address, String phoneNumber, String password, boolean isAdmin) {
        String username = String.join( "" , lName, fName);
        Member newMember = new Member(fName, lName, username, password, phoneNumber, isAdmin);

        // add the member to the next free index in array
        this.memberList[currentMemberCountIndex + 1] = newMember;
        // keep track of the array's length
        currentMemberCountIndex = currentMemberCountIndex + 1;
    }



//    private boolean addMember(Member member) {
//
//    }
}
