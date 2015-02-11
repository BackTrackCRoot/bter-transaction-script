public class URLEncoder {
private StringBuilder str;
public URLEncoder()
{
	str = new StringBuilder();
	str.delete(0, str.length());
}
public int addParameter(String Name,String Vaule)
{
	if(str.equals(""))
	{
		str.append(Name + "=" + Vaule);
	}
	else
	{
		str.append("&" + Name + "=" + Vaule);
	}
	return 0;
}
public int Clear()
{
	str.delete(0, str.length());
	return 0;
}
public String toString()
{
	return str.toString();
}
}
