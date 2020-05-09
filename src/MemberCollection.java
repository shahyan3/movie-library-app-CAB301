public class MemberCollection {

    // private Member member;
    private Member[] memberList;
    private int MAX_MEMBERS = 100;
    private int currentMemberCountIndex;

    public MemberCollection() {
        this.memberList = new Member[MAX_MEMBERS];
        // default root admin user
        Member admin = new Member(0 ,"john", "doe", "staff", "today123", "0423911239", true);
        Member testUser = new Member(1,"mike", "chan", "chanmike", "1111", "0423911239", true);
        Member bri = new Member(2,"briana", "huckstepp", "bri", "1111", "0423911239", true);
        Member lol = new Member(3,"lol", "huckstepp", "lol", "1111", "0423911239", true);
        Member jess = new Member(4,"jess", "huckstepp", "jess", "1111", "0423911239", true);
        Member jef = new Member(5,"jef", "huckstepp", "jef", "1111", "0423911239", true);
        Member chad = new Member(6,"chad", "huckstepp", "chad", "1111", "0423911239", true);
        Member vince = new Member(6,"chad", "huckstepp", "vince", "1111", "0423911239", true);
        Member clancy = new Member(6,"chad", "huckstepp", "clancy", "1111", "0423911239", true);

        this.memberList[0] = admin;
        this.memberList[1] = testUser;
        this.memberList[2] = bri;
        this.memberList[3] = lol;
        this.memberList[4] = jess;
        this.memberList[5] = jef;
        this.memberList[6] = chad;
        this.memberList[7] = vince;
        this.memberList[8] = clancy;
        this.currentMemberCountIndex = 8;    // #TODO make it 0 when i delete testUser!
    }

//    private  Member binarySearch(String target) {
//
//        int l = 0;
//        int r = this.memberList.length - 1;
//        int midpoint = -1;
//
//        while (l <= r) {
//            midpoint = l + (r - l)/2;
//            System.out.println("Set midpoint = "+midpoint);
//
//            if(this.memberList.length == 0) {
//                return null; // list and divided to the point all elements check, and not found.
//            }
//
//                if (this.memberList[midpoint] !=null &&
//                        target.compareTo(this.memberList[midpoint].getUsername()) == 0) { // eventually the only element left after recursive elimination will be midpoint  i.e.
//                    System.out.println("midpoint found: " + this.memberList[midpoint].getUsername());
//
//                    return this.memberList[midpoint]; // member id found for the given "target" member id
//                } else if (this.memberList[midpoint] !=null &&
//                        target.compareTo(this.memberList[midpoint].getUsername()) > 0) { // midpoint greater than target
//                    l = midpoint + 1;
//                    if(this.memberList[midpoint] !=null) {
//                        System.out.println("right side:User searching..." + this.memberList[midpoint].getUsername()
//                        + " at index" + midpoint);
//                    }
//                } else { // midpoint is less than target
//                    r = midpoint - 1;
//                    if(this.memberList[midpoint] !=null) {
//                        System.out.println("left side:User searching..." + this.memberList[midpoint].getUsername()  + " at index" + midpoint);
//                    }
//                }
//
//        }
//
//        return null; // array given is length 0 or 1 element.
//    }

  //    Binary search O(log n) time - CONVERT INTO BINARY SEARCH!!! #TODO convert to binary search
    public Member authenticateMember(String username, String password) {
        for(int i = 0; i < this.memberList.length; i++) {
            Member member;
            if(this.memberList[i].getUsername().equals(username) &&
                    this.memberList[i].getPassword().equals(password)) {
                member = this.memberList[i];
                 return member;
            }
        }
        return null;
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

    // register users - adds a new member in array int id that is in asc ordering starting from memberID=0, sorted.
    public void registerUser(int memberID, String fName, String lName, String address, String phoneNumber, String password, boolean isAdmin) {
        String username = String.join( "" , lName, fName);
        Member newMember = new Member(memberID, fName, lName, username, password, phoneNumber, isAdmin);

        // add the member to the next free index in array
        this.memberList[currentMemberCountIndex + 1] = newMember;
        // keep track of the array's length
        this.currentMemberCountIndex = this.currentMemberCountIndex + 1;

        this.displayAllMembers();
    }

    // the length of the member index array
    public int getCurrentMemberCountIndex() {
        return this.currentMemberCountIndex;
    }

    private void displayAllMembers() {
        for (Member member: this.memberList) {
            if(member != null) {
                System.out.println("Member id: " + member.getMemberID() + ". Username: " + member.getUsername());
            }
        }
    }




}
