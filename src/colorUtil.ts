export default function toRGBA(hex: string): number {

    switch (hex.length) {
        case 3:
            hex = `${hex[0]}${hex[0]}${hex[1]}${hex[1]}${hex[2]}${hex[2]}ff`;
            break
        case 4:
            hex = `${hex[0]}${hex[0]}${hex[1]}${hex[1]}${hex[2]}${hex[2]}${hex[3]}${hex[3]}`;
            break
        case 6:
            hex = `${hex}ff`;
            break
    }

    const r = parseInt(hex.substring(0, 2), 16);
    const g = parseInt(hex.substring(2, 4), 16);
    const b = parseInt(hex.substring(4, 6), 16);
    const a = parseInt(hex.substring(6, 8), 16);

    return (a << 24) + (r << 16) + (g << 8) + (b << 0)
}