export default function toRGBA(nm: string): number {
    const r = parseInt(nm.substring(0, 2), 16);
    const g = parseInt(nm.substring(2, 4), 16);
    const b = parseInt(nm.substring(4, 6), 16);
    const a = parseInt(nm.substring(6, 8), 16);
    return (a << 24) + (r << 16) + (g << 8) + (b << 0)
}