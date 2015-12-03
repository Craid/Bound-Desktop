package de.craid.bound;

import java.nio.ByteBuffer;


public class Test {
	
	public static void main(String args[]){
		testMethod();
		
	}

	
	public static void testMethod(){
		Player player = new Player();
		player.id = 913333243;
		player.position.x = 1223.45667576576576576576f;
		player.position.y = 123123123.2343336512546258728f;
        player.direction.x = 475755443.222f;
        player.direction.y = 5634344646.12f;
        
        System.out.println(player.id);
        System.out.println(player.position.x);
        System.out.println(player.position.y);
        System.out.println(player.direction.x);
        System.out.println(player.direction.y);
        
        System.out.println();
        
        byte b[];
        
        byte a[] = player.send();

        player.id = (int)((a[0]& 0xFF)*(int)Math.pow(2,24)) + (int)((a[1]& 0xFF)*(int)Math.pow(2,16)) + (int)((a[2]& 0xFF)*(int)Math.pow(2, 8)) + (int)(a[3]& 0xFF);

        b = new byte[]{a[4],a[5],a[6],a[7]};
        player.position.x = ByteBuffer.wrap(b).getFloat();
		
        b = new byte[]{a[8],a[9],a[10],a[11]};
        player.position.y = ByteBuffer.wrap(b).getFloat();

        b = new byte[]{a[12],a[13],a[14],a[15]};
        player.direction.x = ByteBuffer.wrap(b).getFloat();

        b = new byte[]{a[16],a[17],a[18],a[19]};
		player.direction.y = ByteBuffer.wrap(b).getFloat();

        System.out.println(player.id);
        System.out.println(player.position.x);
        System.out.println(player.position.y);
        System.out.println(player.direction.x);
        System.out.println(player.direction.y);
        
        System.out.println();
        
        ByteBuffer bb = ByteBuffer.wrap(getPlayerBytes(player));
        bb.putInt(0,8989898);
        
        System.out.println(bb.getInt() + " , " + bb.getFloat() + " , " +bb.getFloat() + " , " + bb.getFloat() + " , " +bb.getFloat());
	
	}
	
	private static byte[] getPlayerBytes(Player player){
		ByteBuffer b = ByteBuffer.allocate(20);
		return b.putInt(player.id).putFloat(player.position.x).putFloat(player.position.y).putFloat(player.direction.x).putFloat(player.direction.y).array();
	}
	
}