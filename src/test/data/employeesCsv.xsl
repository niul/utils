<?xml version='1.0'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text"/>
<xsl:template match="employee">
	<xsl:value-of select="id"/>
	<xsl:text>,</xsl:text>
	<xsl:value-of select="firstName"/>
	<xsl:text>,</xsl:text>
	<xsl:value-of select="lastName"/>
	<xsl:text>,</xsl:text>
	<xsl:value-of select="role"/>
</xsl:template>
</xsl:stylesheet>